package inputport.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import util.trace.Tracer;



public class AHeaderWriteCommand extends AWriteCommand implements HeaderWriteCommand {
	public static final int BYTES_IN_INT =  Integer.SIZE/Byte.SIZE;
	//MessageSelectingRunnable messageSelectingRunnable;
	public AHeaderWriteCommand(MessagingSelectingRunnable theMessageSelectingRunnable,
			SocketChannel theSocketChannel) {
		super(theMessageSelectingRunnable, theSocketChannel, 
				ByteBuffer.allocate(AScatterGatherSelectionManager.BYTES_IN_INT));
		//messageSelectingRunnable = theMessageSelectingRunnable;
		//messageSelectingRunnable.add(this);		
	}
	@Override
	public void makeHeaderFor (WriteCommand theBufferedWrite) {
		ByteBuffer byteBuffer = theBufferedWrite.getWriteBuffer();
		socketChannel = theBufferedWrite.getSocketChannel();
		//writeBuffer.reset();
		writeBuffer.clear();
		writeBuffer.putInt(byteBuffer.limit() - byteBuffer.position());
		writeBuffer.rewind();
	}
	@Override
	public boolean execute() {
		boolean successful = super.execute();
		if (successful) {
			//writeBuffer.clear();
			((MessagingSelectingRunnable) selectingRunnable).add(this);
		} else {
			Tracer.info(this, "unsuccessful write");
		}
		return successful;
	}
	static {
		nextWriteId = - AScatterGatherSelectionManager.getMaxOutstandingWrites();
	}
}
