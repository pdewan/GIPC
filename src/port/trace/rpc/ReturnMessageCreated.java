package port.trace.rpc;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReturnMessageCreated extends TraceableInfo {
	

	public ReturnMessageCreated(String aMessage, Object aSource, Object anOriginal, Object aTransformed) {
		super(aMessage, aSource );
	}
	
	
	public static ReturnMessageCreated newCase(Object aSource, Object anOriginal, Object aTransformed) {
    	String aMessage =  anOriginal+ "->" + aTransformed;
    	ReturnMessageCreated retVal = new ReturnMessageCreated(aMessage, aSource, anOriginal, aTransformed);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
