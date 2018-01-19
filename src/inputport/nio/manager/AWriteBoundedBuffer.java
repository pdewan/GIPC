package inputport.nio.manager;

import inputport.nio.manager.commands.WriteCommand;
import inputport.nio.manager.listeners.WriteBoundedBufferListener;

import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import util.trace.Tracer;
import util.trace.port.nio.SocketChannelInterestOp;
import util.trace.port.nio.WriteBufferIsEmpty;
import util.trace.port.nio.WriteListenerAdded;
import util.trace.port.nio.WriteRequestDequeued;
import util.trace.port.nio.WriteRequestEnqueued;


public class AWriteBoundedBuffer implements WriteBoundedBuffer {
//	public static final int MAX_BUFFERS = 256*4; // 1 K buffers should be enough
	protected List<WriteBoundedBufferListener> writeBoundedBufferListeners = new ArrayList();
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
		WriteRequestEnqueued.newCase(this, anElement);
		contents.add(anElement);
		
	}
	protected void initiateReadCommand() {
		selectionManager.getReadHandler(channel).initiate();
	}
	protected void bufferIsEmpty() {
//		initiateReadCommand();
		SelectionKey key = channel.keyFor(selectionManager.getSelector());
		try {
			key.interestOps(0);
			Tracer.info(this, "New interestops op for:" + channel + " are:" +key.interestOps());
			SocketChannelInterestOp.newCase(this, key, 0);

			
		} catch (CancelledKeyException cke) { // we get this on socket close for some reason
			Tracer.info(this, "Received CancelledKeyException");
			selectionManager.close(key, cke);
		}	
		WriteBufferIsEmpty.newCase(this, channel, writeBoundedBufferListeners);
		for (WriteBoundedBufferListener aListener:writeBoundedBufferListeners) {
			aListener.writeBufferIsEmpty(channel);
		}	
	}
	@Override
	public WriteCommand remove(WriteCommand anElement) {
		WriteCommand retVal = contents.remove();
		WriteRequestDequeued.newCase(this, anElement);
		if (isEmpty()) {
			bufferIsEmpty();
//			selectionManager.getReadHandler(channel).initiate();
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
			if (key.interestOps() != SelectionKey.OP_WRITE ) {
				key.interestOps(SelectionKey.OP_WRITE);
			Tracer.info(this, "New interestops op for:" + channel + " are:" +key.interestOps());
			SocketChannelInterestOp.newCase(this, key, SelectionKey.OP_WRITE);
			}
			

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
	@Override
	public void addListener(WriteBoundedBufferListener aListener) {
//		WriteListenerAdded.newCase(this, channel, aListener);
		writeBoundedBufferListeners.add(aListener);
	}
	

}
