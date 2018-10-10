package util.trace.port.buffer;

import java.nio.ByteBuffer;

import util.trace.TraceableInfo;

public class BufferSendToUnconnectedChannelIgnored extends TraceableInfo {	
	public BufferSendToUnconnectedChannelIgnored(String aMessage, Object aFinder,
			String aSource,
			String aDestination,
			ByteBuffer aByteBuffer) {
		super(aMessage, aFinder);
	}
	public static BufferSendToUnconnectedChannelIgnored newCase(Object aFinder, 
			String aSource,
			String aDestination,
			ByteBuffer aByteBuffer) {    	
		String aMessage =
				aSource + "->" + aDestination + " " +
				
				aByteBuffer ;
		BufferSendToUnconnectedChannelIgnored retVal = new BufferSendToUnconnectedChannelIgnored(aMessage, 
				aFinder,
				aSource, aDestination, aByteBuffer);
    	retVal.announce();
    	return retVal;
	}
}
