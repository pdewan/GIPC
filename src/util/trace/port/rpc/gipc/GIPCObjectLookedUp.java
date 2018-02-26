package util.trace.port.rpc.gipc;

import java.rmi.registry.Registry;

import inputport.rpc.GIPCRegistry;
import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class GIPCObjectLookedUp extends TraceableInfo {
	

	public GIPCObjectLookedUp(String aMessage, Object aSource, Class aClass, String anObjectName, GIPCRegistry aRegistry) {
		super(aMessage, aSource );
	}
	
	
	public static GIPCObjectLookedUp newCase(Object aSource, Class aClass, String anObjectName, GIPCRegistry aRegistry) {
    	String aMessage =  aClass + ":" + anObjectName + "<->" + aRegistry;
    	GIPCObjectLookedUp retVal = new GIPCObjectLookedUp(aMessage, aSource, aClass, anObjectName, aRegistry);
   	    retVal.announce();
    	return retVal;
	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
