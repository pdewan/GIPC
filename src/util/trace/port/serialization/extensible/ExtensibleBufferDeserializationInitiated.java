package util.trace.port.serialization.extensible;

import util.trace.TraceableInfo;

public class ExtensibleBufferDeserializationInitiated extends TraceableInfo {	
	public ExtensibleBufferDeserializationInitiated(String aMessage, Object aValueSerializer,			
			Object anInputBuffer, Class aDeserializedClass) {
		super(aMessage, aValueSerializer);
	}
	public static ExtensibleBufferDeserializationInitiated newCase(Object aValueSerializer, 
			String aSource,
			Object anInputBuffer, Class aDeserializedClass) {    	
		String aMessage = 
//				aValueSerializer + ":" +
				anInputBuffer + "->" +
				
				aDeserializedClass.getClass().getSimpleName(); 
		ExtensibleBufferDeserializationInitiated retVal = 
				new ExtensibleBufferDeserializationInitiated(aMessage, 
				aValueSerializer,
				anInputBuffer, aDeserializedClass );
    	retVal.announce();
    	return retVal;
	}
}
