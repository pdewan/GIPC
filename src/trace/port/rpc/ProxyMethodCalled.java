package trace.port.rpc;

import java.lang.reflect.Method;
import java.util.Arrays;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProxyMethodCalled extends TraceableInfo {
	

	public ProxyMethodCalled(String aMessage, Object aSource, String aDestination, String aName, Method aMethod, Object[] args) {
		super(aMessage, aSource );
	}
	
	
	public static ProxyMethodCalled newCase(Object aSource, String aDestination, String aName, Method aMethod, Object[] args) {
    	String aMessage = "(" + aDestination + "," + aName + ")." +aMethod + "(" + Arrays.toString(args) + ")";
		ProxyMethodCalled retVal = new ProxyMethodCalled(aMessage, aSource, aDestination, aName, aMethod, args);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
