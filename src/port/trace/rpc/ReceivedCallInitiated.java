package port.trace.rpc;

import java.lang.reflect.Method;
import java.util.Arrays;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReceivedCallInitiated extends TraceableInfo {
	

	public ReceivedCallInitiated(String aMessage, Object aSource, Object aTarget,  Method aMethod, Object[] args) {
		super(aMessage, aSource );
	}
	
	
	public static ReceivedCallInitiated newCase(Object aSource, Object aTarget,  Method aMethod, Object[] args) {
    	String aMessage = "(" + aTarget + ")." +aMethod + "(" + Arrays.toString(args) + ")";
		ReceivedCallInitiated retVal = new ReceivedCallInitiated(aMessage, aSource, aTarget, aMethod, args);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
