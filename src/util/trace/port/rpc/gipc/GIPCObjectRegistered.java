package util.trace.port.rpc.gipc;

import java.rmi.registry.Registry;

import inputport.rpc.GIPCRegistry;
import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class GIPCObjectRegistered extends TraceableInfo {
	

	public GIPCObjectRegistered(String aMessage, Object aSource, String anObjectName, Object anObject, GIPCRegistry aRegistry) {
		super(aMessage, aSource );
	}
	
	
	public static GIPCObjectRegistered newCase(Object aSource, String anObjectName, Object anObject, GIPCRegistry aRegistry) {
    	String aMessage = anObject + "<->" + anObjectName + ":" + aRegistry;
    	GIPCObjectRegistered retVal = new GIPCObjectRegistered(aMessage, aSource, anObjectName, anObject, aRegistry);
   	    retVal.announce();
    	return retVal;
	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
