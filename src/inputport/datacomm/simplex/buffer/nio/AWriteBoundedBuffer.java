package inputport.datacomm.simplex.buffer.nio;

import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

import port.trace.buffer.nio.SocketChannelInterestOp;
import util.trace.Tracer;


public class AWriteBoundedBuffer implements WriteBoundedBuffer {
//	public static final int MAX_BUFFERS = 256*4; // 1 K buffers should be enough
	ArrayBlockingQueue<WriteCommand> contents = 
		new ArrayBlockingQueue(AScatterGatherSelectionManager.getMaxOutstandingWrites());
	SocketChannel channel;
	SelectionManager selectionManager;
	public AWriteBoundedBuffer(SelectionManager theSelectionManager, SocketChannel theSocketChannel) {
		channel = theSocketChannel;
		selectionManager = theSelectionManager;		
	}
	@Override
	public boolean isEmpty() {
		return contents.isEmpty();
	}
	@Override
	public void put(WriteCommand anElement) 
//			throws InterruptedException
			{
//		contents.put(anElement);
		// cannot make the caller thread block because 
		// the method it is called by a synchronized method in selection manager
		// so blocking will lock the monitor and thus block the selection thread
		if (contents.size() == AScatterGatherSelectionManager.getMaxOutstandingWrites()) {
			Tracer.error("Overflow of header buffer queue of size:" + AScatterGatherSelectionManager.getMaxOutstandingWrites());
			return;
		}
		contents.add(anElement);
		
	}
	@Override
	public WriteCommand remove(WriteCommand anElement) {
		WriteCommand retVal = contents.remove();
		if (isEmpty()) {
			selectionManager.getReadHandler(channel).initiate();
		}
		return retVal;
	}
	@Override
	public SelectableChannel getChannel() {
		return channel;
	}

	public boolean initiate() {
		if (!channel.isConnected() || 
		    isEmpty()) {
			return false;
		}
		//if (key.interestOps() != SelectionKey.OP_WRITE)
		SelectionKey key = channel.keyFor(selectionManager.getSelector());
		try {
			Tracer.info(this, "Registering write op for:" + channel );
			key.interestOps(SelectionKey.OP_WRITE);
			Tracer.info(this, "New interestops op for:" + channel + " are:" +key.interestOps());
			SocketChannelInterestOp.newCase(this, key, SelectionKey.OP_WRITE);

			return true;
		} catch (CancelledKeyException cke) { // we get this on socket close for some reason
			Tracer.info(this, "Received CancelledKeyException");
			selectionManager.close(key, cke);
			return false;
		}
		
		
	}
	@Override
	public Iterator<WriteCommand> iterator() {
		return contents.iterator();
	}
	

}
