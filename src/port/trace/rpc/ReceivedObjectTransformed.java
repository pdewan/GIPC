package port.trace.rpc;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReceivedObjectTransformed extends TraceableInfo {
	

	public ReceivedObjectTransformed(String aMessage, Object aSource, Object anOriginal, Object aTransformed) {
		super(aMessage, aSource );
	}
	
	
	public static ReceivedObjectTransformed newCase(Object aSource, Object anOriginal, Object aTransformed) {
    	String aMessage =  anOriginal+ "->" + aTransformed;
    	ReceivedObjectTransformed retVal = new ReceivedObjectTransformed(aMessage, aSource, anOriginal, aTransformed);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
