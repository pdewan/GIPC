package util.trace.port.objects;

import util.trace.TraceableInfo;

public class ObjectSerializationInitiated extends TraceableInfo {	
	public ObjectSerializationInitiated(String aMessage, Object aFinder,
			String aDestination,
			Object anObject,
			Object aDelegate) {
		super(aMessage, aFinder);
	}
	public static ObjectSerializationInitiated newCase(Object aFinder, 
			String aDestination,
			Object anObject,
			Object aDelegate) {    	
		String aMessage =
				aDestination + "<-" +
				
				anObject +
				"( via " + aDelegate + ")";
		ObjectSerializationInitiated retVal = new ObjectSerializationInitiated(aMessage, 
				aFinder,
				aDestination,  anObject, aDelegate);
    	retVal.announce();
    	return retVal;
	}
}
