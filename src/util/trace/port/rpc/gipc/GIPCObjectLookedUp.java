package util.trace.port.rpc.gipc;

import inputport.rpc.GIPCRegistry;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class GIPCObjectLookedUp extends TraceableInfo {
	

	public GIPCObjectLookedUp(String aMessage, Object aSource, Object anObject, Class aClass, String anObjectName, GIPCRegistry aRegistry) {
		super(aMessage, aSource );
	}
	
	
	public static GIPCObjectLookedUp newCase(Object aSource, Object anObject, Class aClass, String anObjectName, GIPCRegistry aRegistry) {
    	String aMessage =  anObject + "<->"   + anObjectName + ":" + aClass + ":" + aRegistry;
    	GIPCObjectLookedUp retVal = new GIPCObjectLookedUp(aMessage, aSource, anObject, aClass, anObjectName, aRegistry);
   	    retVal.announce();
    	return retVal;
	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
