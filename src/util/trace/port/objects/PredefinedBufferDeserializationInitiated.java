package util.trace.port.objects;

import java.nio.ByteBuffer;

import util.trace.TraceableInfo;

public class PredefinedBufferDeserializationInitiated extends TraceableInfo {	
	public PredefinedBufferDeserializationInitiated(String aMessage, Object aFinder,
			String aSource,
			ByteBuffer aByteBuffer, Object aDelegate) {
		super(aMessage, aFinder);
	}
	public static PredefinedBufferDeserializationInitiated newCase(Object aFinder, 
			String aSource,
			ByteBuffer aByteBuffer, Object aDelegate) {    	
		String aMessage =
				aSource + "->" +
				
				aByteBuffer + 
				"( via " + aDelegate + ")";
		PredefinedBufferDeserializationInitiated retVal = new PredefinedBufferDeserializationInitiated(aMessage, 
				aFinder,
				aSource,  aByteBuffer, aDelegate);
    	retVal.announce();
    	return retVal;
	}
}
