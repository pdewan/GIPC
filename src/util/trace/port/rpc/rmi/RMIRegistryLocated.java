package util.trace.port.rpc.rmi;

import java.rmi.registry.Registry;

import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class RMIRegistryLocated extends TraceableInfo {
	

	public RMIRegistryLocated(String aMessage, Object aSource, String aHost, int aPort, Registry aRegistry) {
		super(aMessage, aSource );
	}
	
	
	public static RMIRegistryLocated newCase(Object aSource, String aHost, int aPort, Registry aRegistry) {
    	String aMessage =  aHost + ":" + aPort + "->" + aRegistry;
    	RMIRegistryLocated retVal = new RMIRegistryLocated(aMessage, aSource, aHost, aPort, aRegistry);
   	    retVal.announce();
    	return retVal;
	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
