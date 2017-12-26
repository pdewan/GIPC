package trace.port.rpc;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProxyCacheInvalidated extends TraceableInfo {
	

	public ProxyCacheInvalidated(String aMessage, Object aSource) {
		super(aMessage, aSource );
	}
	
	
	public static ProxyCacheInvalidated newCase(Object aSource) {
    	String aMessage =  "Received a call";
    	ProxyCacheInvalidated retVal = new ProxyCacheInvalidated(aMessage, aSource);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
