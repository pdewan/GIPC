package port.trace.buffer;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ClientNameAssociatedWithPort extends TraceableInfo {
	

	public ClientNameAssociatedWithPort(String aMessage, Object aSource, String aClientName, Object aPort) {
		super(aMessage, aSource );
	}
	
	
	public static ClientNameAssociatedWithPort newCase(Object aSource, String aClientName,  Object aPort) {
    	String aMessage =  aClientName + "<->" + aPort;
    	ClientNameAssociatedWithPort retVal = new ClientNameAssociatedWithPort(aMessage, aSource, aClientName, aPort);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
