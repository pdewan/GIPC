package trace.port.rpc;

import java.lang.reflect.Method;
import java.util.Arrays;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReceivedCallInitiated extends TraceableInfo {
	

	public ReceivedCallInitiated(String aMessage, Object aSource, Object aTarget,  Method aMethod, Object[] args, String aSender) {
		super(aMessage, aSource );
	}
	
	
	public static ReceivedCallInitiated newCase(Object aSource, Object aTarget,  Method aMethod, Object[] args, String aSender) {
    	String aMessage = aSender + "-->" +  "(" + aTarget + ")." +aMethod + "(" + Arrays.toString(args) + ")";
		ReceivedCallInitiated retVal = new ReceivedCallInitiated(aMessage, aSource, aTarget, aMethod, args, aSender);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
