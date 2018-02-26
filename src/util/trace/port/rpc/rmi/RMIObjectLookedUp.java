package util.trace.port.rpc.rmi;

import java.rmi.registry.Registry;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class RMIObjectLookedUp extends TraceableInfo {
	

	public RMIObjectLookedUp(String aMessage, Object aSource, Object anObject, String anObjectName, Registry aRegistry) {
		super(aMessage, aSource );
	}
	
	
	public static RMIObjectLookedUp newCase(Object aSource, Object anObject, String anObjectName, Registry aRegistry) {
    	String aMessage = anObject + "<->" + anObjectName + ":" + aRegistry;
    	RMIObjectLookedUp retVal = new RMIObjectLookedUp(aMessage, aSource, anObject, anObjectName, aRegistry);
   	    retVal.announce();
    	return retVal;
	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
