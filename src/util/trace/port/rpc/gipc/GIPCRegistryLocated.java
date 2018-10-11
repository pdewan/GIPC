package util.trace.port.rpc.gipc;

import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class GIPCRegistryLocated extends TraceableInfo {
	

	public GIPCRegistryLocated(String aMessage, Object aSource, String aHost, int aPort, String aClientName) {
		super(aMessage, aSource );
	}
	
	
	public static GIPCRegistryLocated newCase(Object aSource, String aHost, int aPort, String aClientName) {
    	String aMessage =  aHost + ":" + aPort + "<-" + aClientName;
    	GIPCRegistryLocated retVal = new GIPCRegistryLocated(aMessage, aSource, aHost, aPort, aClientName);
   	    retVal.announce();
    	return retVal;
	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
