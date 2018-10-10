package util.trace.port.buffer;

import util.trace.TraceableInfo;

public class NumberBytesReceived extends TraceableInfo {	
	public NumberBytesReceived(String aMessage, 
			Object aFinder,
			String aSource,
			String aDestination,
			int aNumber
			) {
		super(aMessage, aFinder);
	}
	public static NumberBytesReceived newCase(Object aFinder, 
			String aSource,
			String aDestination,
			int aNumber
			) {    	
		String aMessage =
				aSource + "->" + aDestination + " " +
				
				aNumber + " bytes ";
		NumberBytesReceived retVal = new NumberBytesReceived(aMessage, 
				aFinder,
				aSource, aDestination, aNumber);
    	retVal.announce();
    	return retVal;
	}
}
