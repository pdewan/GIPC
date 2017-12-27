package inputport.nio;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import trace.port.nio.SocketChannelInterestOp;
import trace.port.nio.SocketChannelRead;
import util.trace.Tracer;


public class AReadCommand extends AnAbstractNIOCommand implements ReadCommand {
	public static final int READ_BUFFER_SIZE = 256*4*256*4*4; // with late comer, even 1 M was not enough in simple demo
	SocketChannel socketChannel;
	SelectionManager selectionManager;
	ByteBuffer readBuffer;
	List<SocketChannelCloseListener> closeListeners = new ArrayList();
	List<SocketChannelReadListener> readListeners = new ArrayList();
	public AReadCommand(SelectionManager theSelectionManager,
			SocketChannel theSocketChannel, Integer aNextInterestOps) {
		super(aNextInterestOps);
		socketChannel = theSocketChannel;
		selectionManager = theSelectionManager;	
		allocateReadState();
	}
	public AReadCommand(SelectionManager theSelectionManager,
			SocketChannel theSocketChannel) {
		this(theSelectionManager, theSocketChannel, null);
	}
	@Override
	public SelectableChannel getChannel() {
		return socketChannel;
	}
	@Override
	public boolean initiate() {
		Tracer.info(this, "Selector registering read as no pending writes");
		SelectionKey selectionKey = socketChannel.keyFor(selectionManager.getSelector());
		selectionKey.interestOps(SelectionKey.OP_READ);
		Tracer.info(this, "New selection ops for channel: " + selectionKey.channel() + " are " + selectionKey.interestOps());
		SocketChannelInterestOp.newCase(this, selectionKey, SelectionKey.OP_READ);

		return false;
	}
	@Override
	public boolean execute() {
		try {
			processRead();
			return true;
		} catch (IOException e) {
//			e.printStackTrace();
			System.err.println ("AReadCommand for " + socketChannel + ":" +  e.getMessage());
			SelectionKey key = socketChannel.keyFor(selectionManager.getSelector());
			selectionManager.close(key, e);
			notifyCloseListeners(socketChannel, e);
			return false;
		}
	}
	protected void processRead() throws IOException {
		
		Tracer.info(this, "Processing read on channel:" + socketChannel);
		readIntoBuffer();
		readBuffer.flip();// why flip if we are clearing later? To do the wrap correctly?
		Tracer.info(this, "Read buffer after flip:" + readBuffer);
		ByteBuffer messageBuffer = ByteBuffer.wrap(readBuffer.array(), 0, readBuffer.remaining());
		notifyRead(socketChannel, messageBuffer, readBuffer.remaining());
		readBuffer.clear();	
		Tracer.info(this, "Cleared read buffer");
	}
	protected int  readIntoBuffer() throws IOException {
		
		int bytesRead = socketChannel.read(readBuffer);
		SocketChannelRead.newCase(this, socketChannel, readBuffer);
		if (readBuffer.position() == readBuffer.capacity()) {
			Tracer.error("Read Buffer Overflow. Bytes read : " + bytesRead);
		}
		Tracer.info(this, "Read: " + bytesRead + " bytes into buffer: " + readBuffer);
		if (bytesRead < 0)  {
			throw new EOFException("An existing connection was normally closed by the remote host.");				
		}
		return bytesRead;
			
     }
	protected void notifyRead(SocketChannel theSocketChannel, ByteBuffer theBuffer, int length ) {
		for (SocketChannelReadListener readListener:readListeners)
//		if (readListener != null)
			readListener.socketChannelRead(theSocketChannel, theBuffer, length);
		
	}
	protected void allocateReadState() {
		readBuffer = ByteBuffer.allocate(READ_BUFFER_SIZE);
		Tracer.info(this, "Marked and allocated read buffer:" + readBuffer + " of size " + READ_BUFFER_SIZE );
		readBuffer.mark();
		
	}
	
	@Override
	public void addReadListener(SocketChannelReadListener theReadListener) {
		readListeners.add(theReadListener);
		
	}
	public void addCloseListener(SocketChannelCloseListener aListener) {
		if (closeListeners.contains(aListener))
			return;
		closeListeners.add(aListener);
	}
	public void notifyCloseListeners(SocketChannel theSocketChannel, Exception anException) {
		
	for (SocketChannelCloseListener closeListener:closeListeners)
	    closeListener.closed(theSocketChannel, anException);
	}
	

}
