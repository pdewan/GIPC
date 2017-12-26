package trace.port.rpc;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class SentObjectTransformed extends TraceableInfo {
	

	public SentObjectTransformed(String aMessage, Object aSource, Object anOriginal, Object aTransformed, Class aReturnType) {
		super(aMessage, aSource );
	}
	
	
	public static SentObjectTransformed newCase(Object aSource, Object anOriginal, Object aTransformed, Class aReturnType) {
		String aMessage =  anOriginal+ "->" + aTransformed +":" + aReturnType;
    	SentObjectTransformed retVal = new SentObjectTransformed(aMessage, aSource, anOriginal, aTransformed, aReturnType);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
