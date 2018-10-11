package util.trace.port.serialization.extensible;

import util.trace.TraceableInfo;

public class ExtensibleValueSerializationFinished extends TraceableInfo {
	public ExtensibleValueSerializationFinished(String aMessage,
			Object aValueSerializer, Object anObject, Object anOutputBuffer, Object aVisitedObjects) {
		super(aMessage, aValueSerializer);
	}

	public static ExtensibleValueSerializationFinished newCase(Object aValueSerializer,
			Object anObject, Object anOutputBuffer, Object aVisitedObjects) {
		String aMessage = 
//				aValueSerializer + ":" + 
		anOutputBuffer + "<--" + anObject + "(" + aVisitedObjects + ")";;
		ExtensibleValueSerializationFinished retVal = new ExtensibleValueSerializationFinished(
				aMessage, aValueSerializer, anObject, anOutputBuffer, aVisitedObjects);
		retVal.announce();
		return retVal;
		
	}
}
