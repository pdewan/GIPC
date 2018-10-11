package util.trace.port.buffer;

import java.nio.ByteBuffer;

import inputport.ConnectionManager;
import util.trace.TraceableInfo;

public class BufferSendFinished extends TraceableInfo {	
	public BufferSendFinished(String aMessage, Object aFinder,
			String aSource,
			String aDestination,
			ByteBuffer aByteBuffer,
			ConnectionManager aBufferChannel) {
		super(aMessage, aFinder);
	}
	public static BufferSendFinished newCase(Object aFinder, 
			String aSource,
			String aDestination,
			ByteBuffer aByteBuffer,
			ConnectionManager aBufferChannel) {    	
		String aMessage =
				aSource + "->" + aDestination + " " +
				
				aByteBuffer +
				"(" +
				 aBufferChannel + ")";
		BufferSendFinished retVal = new BufferSendFinished(aMessage, 
				aFinder,
				aSource, aDestination, aByteBuffer, aBufferChannel);
    	retVal.announce();
    	return retVal;
	}
}
