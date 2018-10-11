package util.trace.port.serialization.extensible;

import util.trace.TraceableInfo;

public class ExtensibleBufferDeserializationFinished extends TraceableInfo {
	public ExtensibleBufferDeserializationFinished(String aMessage,
			Object aValueSerializer, Object anInputBuffer,
			Object anOutputObject,
			Object aRetrievedObjects) {
		super(aMessage, aValueSerializer);
	}

	public static ExtensibleBufferDeserializationFinished newCase(
			Object aValueSerializer, String aSource, Object anInputBuffer,
			Object anOutputObject,
			Object aRetrievedObjects) {
		String aMessage = 
//				aValueSerializer + ":" + 
				anInputBuffer + "->" + anOutputObject + "(" + aRetrievedObjects + ")";
		ExtensibleBufferDeserializationFinished retVal = new ExtensibleBufferDeserializationFinished(
				aMessage, aValueSerializer, anInputBuffer, anOutputObject, aRetrievedObjects);
		retVal.announce();
		return retVal;
	}
}
