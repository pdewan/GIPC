package trace.port.rpc;

import java.lang.reflect.Method;
import java.util.Arrays;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProxyMethodReturnsCachedValue extends TraceableInfo {
	

	public ProxyMethodReturnsCachedValue(String aMessage, Object aSource, String aDestination, String aName, Method aMethod, Object[] args, Object aResult) {
		super(aMessage, aSource );
	}
	
	
	public static ProxyMethodReturnsCachedValue newCase(Object aSource, String aDestination, String aName, Method aMethod, Object[] args, Object aResult) {
    	String aMessage = aResult + "<-" + "(" + aDestination + "," + aName + ")." +aMethod + "(" + Arrays.toString(args) + ")";
		ProxyMethodReturnsCachedValue retVal = new ProxyMethodReturnsCachedValue(aMessage, aSource, aDestination, aName, aMethod, args, aResult);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
