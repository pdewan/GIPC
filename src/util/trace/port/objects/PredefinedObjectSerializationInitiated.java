package util.trace.port.objects;

import util.trace.TraceableInfo;

public class PredefinedObjectSerializationInitiated extends TraceableInfo {	
	public PredefinedObjectSerializationInitiated(String aMessage, Object aFinder,
			String aDestination,
			Object anObject,
			Object aDelegate) {
		super(aMessage, aFinder);
	}
	public static PredefinedObjectSerializationInitiated newCase(Object aFinder, 
			String aDestination,
			Object anObject,
			Object aDelegate) {    	
		String aMessage =
				aDestination + "<-" +
				
				anObject +
				"( via " + aDelegate + ")";
		PredefinedObjectSerializationInitiated retVal = new PredefinedObjectSerializationInitiated(aMessage, 
				aFinder,
				aDestination,  anObject, aDelegate);
    	retVal.announce();
    	return retVal;
	}
}
