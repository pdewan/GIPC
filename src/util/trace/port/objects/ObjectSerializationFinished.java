package util.trace.port.objects;

import java.nio.ByteBuffer;

import util.trace.TraceableInfo;

public class ObjectSerializationFinished extends TraceableInfo {	
	static int totalBytesSerialized;
	public ObjectSerializationFinished(String aMessage, Object aFinder,
			String aDestination,
			ByteBuffer aByteBuffer,
			Object anObject) {
		super(aMessage, aFinder);
	}
	public static ObjectSerializationFinished newCase(Object aFinder, 
			String aDestination,
			ByteBuffer aByteBuffer, Object anObject) { 
		totalBytesSerialized += (aByteBuffer.limit() - aByteBuffer.position());
		String aMessage =
				aDestination + "->" +
				anObject + "->" +
				aByteBuffer + "(Total serialized bytes = " + (totalBytesSerialized) + ")";
		ObjectSerializationFinished retVal = new ObjectSerializationFinished(aMessage, 
				aFinder,
				aDestination,  aByteBuffer, anObject);
    	retVal.announce();
    	return retVal;
	}
}
