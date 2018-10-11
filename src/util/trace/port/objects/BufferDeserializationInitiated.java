package util.trace.port.objects;

import java.nio.ByteBuffer;

import util.trace.TraceableInfo;

public class BufferDeserializationInitiated extends TraceableInfo {	
	public BufferDeserializationInitiated(String aMessage, Object aFinder,
			String aSource,
			ByteBuffer aByteBuffer, Object aDelegate) {
		super(aMessage, aFinder);
	}
	public static BufferDeserializationInitiated newCase(Object aFinder, 
			String aSource,
			ByteBuffer aByteBuffer, Object aDelegate) {    	
		String aMessage =
				aSource + "->" +
				
				aByteBuffer + 
				"( via " + aDelegate + ")";
		BufferDeserializationInitiated retVal = new BufferDeserializationInitiated(aMessage, 
				aFinder,
				aSource,  aByteBuffer, aDelegate);
    	retVal.announce();
    	return retVal;
	}
}
