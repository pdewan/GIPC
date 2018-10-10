package util.trace.port.serialization.extensible;

import util.trace.TraceableInfo;

public class ExtensibleValueSerializationInitiated extends TraceableInfo {
	public ExtensibleValueSerializationInitiated(String aMessage,
			Object aValueSerializer, Object anObject, Object anInputBuffer) {
		super(aMessage, aValueSerializer);
	}

	public static ExtensibleValueSerializationInitiated newCase(Object aValueSerializer,
			Object anObject, Object anInputBuffer) {
		String aMessage = 
//				aValueSerializer + ":" + 
						anInputBuffer + "<--" + anObject;
		ExtensibleValueSerializationInitiated retVal = new ExtensibleValueSerializationInitiated(
				aMessage, aValueSerializer, anObject, anInputBuffer);
		retVal.announce();
		return retVal;		
	}
}
