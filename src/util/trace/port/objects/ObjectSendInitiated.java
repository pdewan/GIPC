package util.trace.port.objects;

import util.trace.TraceableInfo;

public class ObjectSendInitiated extends TraceableInfo {	
	public ObjectSendInitiated(String aMessage, Object aFinder,
			String aSource,
			String aDestination,
			Object anObject) {
		super(aMessage, aFinder);
	}
	public static ObjectSendInitiated newCase(Object aFinder, 
			String aSource,
			String aDestination,
			Object anObject) {    	
		String aMessage =
				aSource + "->" + aDestination ;
		ObjectSendInitiated retVal = new ObjectSendInitiated(aMessage, 
				aFinder,
				aSource, aDestination, anObject);
    	retVal.announce();
    	return retVal;
	}
}
