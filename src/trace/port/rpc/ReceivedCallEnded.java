package trace.port.rpc;

import java.lang.reflect.Method;
import java.util.Arrays;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReceivedCallEnded extends TraceableInfo {
	

	public ReceivedCallEnded(String aMessage, Object aSource, Object aTarget,  Method aMethod, Object[] args, Object aRetVal) {
		super(aMessage, aSource );
	}
	
	
	public static ReceivedCallEnded newCase(Object aSource, Object aTarget,  Method aMethod, Object[] args, Object aRetVal) {
    	String aMessage = "(" + aTarget + ")." +aMethod + "(" + Arrays.toString(args) + ")=" + aRetVal;
		ReceivedCallEnded retVal = new ReceivedCallEnded(aMessage, aSource, aTarget, aMethod, args, aRetVal);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
