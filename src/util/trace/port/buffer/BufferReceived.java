package util.trace.port.buffer;

import java.nio.ByteBuffer;

import inputport.ConnectionManager;
import util.trace.TraceableInfo;

public class BufferReceived extends TraceableInfo {	
	public BufferReceived(String aMessage, Object aFinder,
			String aSource,
			String aDestination,
			ByteBuffer aByteBuffer,
			ConnectionManager aBufferChannel) {
		super(aMessage, aFinder);
	}
	public static BufferReceived newCase(Object aFinder, 
			String aSource,
			String aDestination,
			ByteBuffer aByteBuffer,
			ConnectionManager aBufferChannel) {    	
		String aMessage =
				aSource + "->" + aDestination + " " +
				
				aByteBuffer +
				"(" +
				 aBufferChannel + ")";
		BufferReceived retVal = new BufferReceived(aMessage, 
				aFinder,
				aSource, aDestination, aByteBuffer, aBufferChannel);
    	retVal.announce();
    	return retVal;
	}
}
