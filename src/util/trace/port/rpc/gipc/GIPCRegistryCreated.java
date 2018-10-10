package util.trace.port.rpc.gipc;

import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class GIPCRegistryCreated extends TraceableInfo {
	

	public GIPCRegistryCreated(String aMessage, Object aSource, int aPort) {
		super(aMessage, aSource );
	}
	
	
	public static GIPCRegistryCreated newCase(Object aSource, int aPort) {
    	String aMessage =  Integer.toString(aPort);
    	GIPCRegistryCreated retVal = new GIPCRegistryCreated(aMessage, aSource, aPort);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
