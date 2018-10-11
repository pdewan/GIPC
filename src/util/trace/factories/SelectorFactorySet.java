package util.trace.factories;

import util.trace.TraceableInfo;

public class SelectorFactorySet extends TraceableInfo {	
	public SelectorFactorySet(String aMessage, Object aFinder, 
			Object aFactory) {
		super(aMessage, aFinder);
	}
	public static SelectorFactorySet newCase(Object aSource, 			
			Object aFactory) {    	
		String aMessage = 
				aFactory.toString();
		SelectorFactorySet retVal = new SelectorFactorySet(aMessage, 
				aSource, 
				aFactory);
    	retVal.announce();
    	return retVal;
	}
//	static {
//		FactoryTraceUtility.setTracing();
//	}
}
