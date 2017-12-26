package trace.port.rpc;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.Tracer;
@DisplayToString(true)
@ComponentWidth(1000)
public class RemoteCallGenerated extends CallInfo {
	

	public RemoteCallGenerated(String aMessage, Object aSource, Object aDestination, String aRemoteEndPoint, RemoteCall aCall) {
		super(aMessage, aSource, aDestination, aRemoteEndPoint, aCall);
	}
	
	
	public static RemoteCallGenerated newCase(Object aSource, Object aDestination, String aRemoteEndPoint, RemoteCall aCall) {
    	String aMessage = "Generated:" +  aCall + " and forwarded to " +  aDestination ;
    	RemoteCallGenerated retVal = new RemoteCallGenerated(aMessage, aSource, aDestination, aRemoteEndPoint, aCall);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
		Tracer.setKeywordDisplayStatus(RemoteCallGenerated.class, true);
	}

}
