package util.trace.port.rpc.rmi;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class RMIRegistryCreated extends TraceableInfo {
	

	public RMIRegistryCreated(String aMessage, Object aSource, int aPort) {
		super(aMessage, aSource );
	}
	
	
	public static RMIRegistryCreated newCase(Object aSource, int aPort) {
    	String aMessage =  Integer.toString(aPort);
    	RMIRegistryCreated retVal = new RMIRegistryCreated(aMessage, aSource, aPort);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
