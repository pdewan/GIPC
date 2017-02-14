package port.trace;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ClientNameLookedUp extends TraceableInfo {
	

	public ClientNameLookedUp(String aMessage, Object aSource, String aClientName, Object aPort) {
		super(aMessage, aSource );
	}
	
	
	public static ClientNameLookedUp newCase(Object aSource, String aClientName,  Object aPort) {
    	String aMessage =  aClientName + "<->" + aPort;
    	ClientNameLookedUp retVal = new ClientNameLookedUp(aMessage, aSource, aClientName, aPort);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
