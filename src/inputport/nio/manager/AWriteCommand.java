package inputport.nio.manager;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import util.trace.Tracer;
import util.trace.port.nio.SocketChannelInterestOp;
import util.trace.port.nio.SocketChannelWritten;



public class AWriteCommand extends AnAbstractNIOCommand implements WriteCommand  {
	SocketChannel socketChannel;
	ByteBuffer writeBuffer;
	List<SocketChannelWriteListener> writeListeners = new ArrayList() ;
//	List<SocketChannelCloseListener> closeListeners = new ArrayList();
	SelectionManager selectingRunnable;
	static int nextWriteId = 0;
	int writeId;
	public AWriteCommand (
			SelectionManager aSelectingRunnable, 
			SocketChannel  aSocketChannel, 
			ByteBuffer aWriteBuffer,
			Integer aNextInterestOps) {
		super (aSocketChannel, aNextInterestOps);
		socketChannel = aSocketChannel;
//		writeBuffer = theWriteBuffer.duplicate();
		writeBuffer = aWriteBuffer;
	
		selectingRunnable = aSelectingRunnable;
		writeId = nextWriteId;
//		Tracer.info(this, "ABufferedWrite with id:" + writeId + " contents:" + writeBuffer);
		nextWriteId++;
//		if (aWriteListener != null)
//			addwriteListener( aWriteListener);
//		if (aCloseListener != null)
//			selectingRunnable.getReadHandler(aSocketChannel).addCloseListener( aCloseListener);
	}
	public AWriteCommand (
			SelectionManager aSelectingRunnable, 
			SocketChannel  aSocketChannel, 
			ByteBuffer aWriteBuffer) {
		this(aSelectingRunnable, aSocketChannel, aWriteBuffer, null);		
	}
	@Override
	public void addwriteListener(SocketChannelWriteListener aListener) {
		writeListeners.add(aListener);
	}
//	@Override
//	public void register(SocketChannelCloseListener aListener) {
//		closeListeners.add(aListener);
//	}
	@Override
	public SocketChannel getSocketChannel() {
		return socketChannel;
	}
	@Override
	public void setSocketChannel(SocketChannel theSocketChannel) {
		socketChannel = theSocketChannel;
	}	
	@Override
	public ByteBuffer getWriteBuffer() {
		return writeBuffer;
	}
	@Override
	public boolean execute() {
		// whjy are we guaranteed connected?
//		if (!socketChannel.isConnected())
//			return false;
		try {
//			byte[] prefixBytes = new byte[2];
//			byte[] suffixBytes = new byte[writeBuffer.limit() - 2];
//			writeBuffer.get(prefixBytes);
//			writeBuffer.get(suffixBytes);
//			ByteBuffer prefixBuffer = ByteBuffer.wrap(prefixBytes);
//			writeBuffer = ByteBuffer.wrap(suffixBytes);
//			socketChannel.write(prefixBuffer);
//			String writeBufferToString = writeBuffer.toString();
//			Message.info("writing to channel " + socketChannel + " buffer:" + getId() + " with contents" + writeBuffer);
			SocketChannelWritten.newCase(this, socketChannel, writeBuffer);
			socketChannel.write(writeBuffer);
			if (writeBuffer.remaining() > 0) {
				SelectionKey selectionKey = socketChannel.keyFor(selectingRunnable.getSelector());
				selectionKey.interestOps(SelectionKey.OP_WRITE);
//				Tracer.info(this, "Putting buffer back as not all of it written. Socket channel: " + socketChannel + " interst ops: " + selectionKey.interestOps());
				SocketChannelInterestOp.newCase(this, selectionKey, SelectionKey.OP_WRITE);

				//				theSelectingRunnable.putBufferedWrite(this);
				return false; // this means the buffer will not be removed by the writer
			}
			writeBuffer.flip(); // so that it gets into read mode
			Tracer.info(this, "written to channel" + socketChannel + " flipped buffer:" + getId() + " with contents" + writeBuffer.toString());
//			Tracer.info(this, "flipping buffer for reading by write listeners:" + writeBuffer.toString());
			for (SocketChannelWriteListener writeListener:writeListeners)
				writeListener.written(socketChannel, writeBuffer, getId());
			Tracer.info(this, "notified listeners about write");

			return true;
			
		} catch (Exception e) {
//			e.printStackTrace();
			System.err.println("WriteCommand:" + e.getMessage());
			SelectionKey key = socketChannel.keyFor(selectingRunnable.getSelector());
			selectingRunnable.close(key, e);
			selectingRunnable.getReadHandler(socketChannel).notifyCloseListeners(socketChannel, e);
//			
//			for (SocketChannelCloseListener closeListener:closeListeners)
//			    closeListener.closed(socketChannel, e);
			return false;
		}
	}
	@Override
	public int getId() {
		return writeId;
	}
	

	
}
