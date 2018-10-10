package util.trace.port.rpc;

import java.lang.reflect.Method;
import java.util.Arrays;

import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProxyPureFunctionCalled extends TraceableInfo {
	

	public ProxyPureFunctionCalled(String aMessage, Object aSource, String aDestination, String aName, Method aMethod, Object[] args) {
		super(aMessage, aSource );
	}
	
	
	public static ProxyPureFunctionCalled newCase(Object aSource, String aDestination, String aName, Method aMethod, Object[] args) {
    	String aMessage = "(" + aDestination + "," + aName + ")." +aMethod + "(" + Arrays.toString(args) + ")";
		ProxyPureFunctionCalled retVal = new ProxyPureFunctionCalled(aMessage, aSource, aDestination, aName, aMethod, args);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
