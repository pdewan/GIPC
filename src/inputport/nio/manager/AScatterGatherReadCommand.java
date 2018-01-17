package inputport.nio.manager;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import util.trace.Tracer;
import util.trace.port.nio.SocketChannelFullMessageRead;
import util.trace.port.nio.SocketChannelHeaderRead;
import util.trace.port.nio.SocketChannelPartialMessageFound;


public class AScatterGatherReadCommand extends AReadCommand{
	boolean expectingHeader = true ;
	boolean partialMessageReceived = false;
	int messageLength = 0;
//	public static final int BYTES_IN_INT =  Integer.SIZE/Byte.SIZE;



	public AScatterGatherReadCommand(SelectionManager theSelectionManager,
			SocketChannel theSocketChannel) {
		super(theSelectionManager, theSocketChannel);
	}
	@Override
	protected void processRead() throws IOException {
		readIntoBuffer();
		partialMessageReceived = false; // have received new data, so older value may not be correct
		int numIterations = 0;
		while (true) {
			if (readBuffer.remaining() == 0 || partialMessageReceived) {
				if (!maybeClearBuffer(readBuffer,partialMessageReceived, expectingHeader ))
				resetToWritePastLimit(readBuffer);
				break;				
			} if (expectingHeader) {
				extractMessageLength(socketChannel);
			} else {
				maybeExtractMessageAndNotifyReadListener(socketChannel);
			}
			numIterations++;
		}
		// reset if no state is required, the readBuffer check is probably redundant given the loop above
	
	}
	protected static void resetToReadFromMark(ByteBuffer aByteBuffer) {
		int oldPosition = aByteBuffer.position();
		if (oldPosition == 0) // no mark
			return;
		aByteBuffer.reset();
		aByteBuffer.limit(oldPosition);		
	}
	protected static void resetToWritePastLimit(ByteBuffer theByteBuffer) {
		theByteBuffer.position(theByteBuffer.limit());
		theByteBuffer.limit(theByteBuffer.capacity());
	}
	protected boolean maybeClearBuffer(ByteBuffer aByteBuffer, boolean partialMessageReceived, boolean expectingHeader) {
		boolean clearBuffer = aByteBuffer.remaining() == 0 &&
							  !partialMessageReceived && 
							  expectingHeader;
		if (clearBuffer) {
			Tracer.info(this, "Clearing read buffer as all bytes of message received and consumed");
			aByteBuffer.clear();
			aByteBuffer.mark();
		}
		return clearBuffer;
	}
	protected void extractMessageLength (SocketChannel socketChannel) {
		try {
			int totalBytesRead = readBuffer.remaining();
			while (totalBytesRead < AScatterGatherSelectionManager.BYTES_IN_INT) {
				Tracer.info(this, "Header bytes read less than header size, polling for remaining bytes");
				resetToWritePastLimit(readBuffer);
				Tracer.info(this, "Set position to limit, and limit to capacity:" + readBuffer);				
				int nextBytesRead = readIntoBuffer();

				totalBytesRead += nextBytesRead;
			
			}
//			channelToExpectingHeader.put(socketChannel, false);
			expectingHeader = false;
//			if (readBuffer.remaining() > 4) {
//				System.out.println("got more than header");
//			}
			messageLength = readBuffer.getInt();
			
//			if (messageLength > 16) {
//				System.out.println("something wrong");
//
//			}
			
			Tracer.info(this, "Message length extracted:" + messageLength);
			readBuffer.mark();
			SocketChannelHeaderRead.newCase(this, socketChannel, readBuffer, messageLength);
			Tracer.info(this, "Marked buffer at current position:" + readBuffer);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}	

	protected void maybeExtractMessageAndNotifyReadListener(SocketChannel socketChannel) {
		Tracer.info(this, "Read message buffer:" + readBuffer + " from " + socketChannel);
		if (readBuffer.remaining() < messageLength) {
			SocketChannelPartialMessageFound.newCase(this, socketChannel, readBuffer, readBuffer.remaining());
			Tracer.info(this, "Did not receive full message length:" + messageLength);	
			Tracer.info(this, "Not changing position or limit");
			partialMessageReceived = true;
			return;
		}
		Tracer.info(this, "Received full message");;	
		expectingHeader = true;
		partialMessageReceived = false;
		ByteBuffer messageBuffer = ByteBuffer.wrap(readBuffer.array(), readBuffer.position(), messageLength);
		Tracer.info(this, "Sending listener new message byte buffer, sharing the array of the read buffer:" + messageBuffer);
		SocketChannelFullMessageRead.newCase(this, socketChannel, readBuffer, messageLength);
		notifyRead(socketChannel, messageBuffer, messageLength);
		// should we not clear the buffer?
		readBuffer.position(readBuffer.position() + messageLength);
		readBuffer.mark();
		Tracer.info(this, "Advanced read buffer position to first unconsumed byte and marked position:" + readBuffer);		


	}
	@Override
	protected int  readIntoBuffer() throws IOException {
		int bytesRead = super.readIntoBuffer();
		resetToReadFromMark(readBuffer);
		Tracer.info(this, "Resetting buffer  to mark and limit to position:" + readBuffer);
		return bytesRead;		
	}
	

}
