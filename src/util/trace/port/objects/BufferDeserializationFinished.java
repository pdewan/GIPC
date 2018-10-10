package util.trace.port.objects;

import java.nio.ByteBuffer;

import util.trace.TraceableInfo;

public class BufferDeserializationFinished extends TraceableInfo {	
	public BufferDeserializationFinished(String aMessage, Object aFinder,
			String aSource,
			ByteBuffer aByteBuffer,
			Object anObject) {
		super(aMessage, aFinder);
	}
	public static BufferDeserializationFinished newCase(Object aFinder, 
			String aSource,
			ByteBuffer aByteBuffer, Object anObject) {    	
		String aMessage =
				aSource + "->" +
				anObject + " <-" +
				aByteBuffer ;
		BufferDeserializationFinished retVal = new BufferDeserializationFinished(aMessage, 
				aFinder,
				aSource,  aByteBuffer, anObject);
    	retVal.announce();
    	return retVal;
	}
}
