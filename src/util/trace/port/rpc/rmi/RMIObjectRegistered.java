package util.trace.port.rpc.rmi;

import java.rmi.registry.Registry;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class RMIObjectRegistered extends TraceableInfo {
	

	public RMIObjectRegistered(String aMessage, Object aSource, String anObjectName, Object anObject, Registry aRegistry) {
		super(aMessage, aSource );
	}
	
	
	public static RMIObjectRegistered newCase(Object aSource, String anObjectName, Object anObject, Registry aRegistry) {
    	String aMessage = anObject + "<->" + anObjectName + ":" + aRegistry;
    	RMIObjectRegistered retVal = new RMIObjectRegistered(aMessage, aSource, anObjectName, anObject, aRegistry);
   	    retVal.announce();
    	return retVal;
	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
