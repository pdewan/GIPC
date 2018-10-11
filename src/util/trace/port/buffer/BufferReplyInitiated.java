package util.trace.port.buffer;

import java.nio.ByteBuffer;

import util.trace.TraceableInfo;

public class BufferReplyInitiated extends TraceableInfo {	
	public BufferReplyInitiated(String aMessage, Object aFinder,
			String aSource,
			ByteBuffer aByteBuffer) {
		super(aMessage, aFinder);
	}
	public static BufferReplyInitiated newCase(Object aFinder, 
			String aSource,
			ByteBuffer aByteBuffer) {    	
		String aMessage =
				aSource + "-> last sender" +
				
				aByteBuffer ;;
		BufferReplyInitiated retVal = new BufferReplyInitiated(aMessage, 
				aFinder,
				aSource,  aByteBuffer);
    	retVal.announce();
    	return retVal;
	}
}
