package trace.port.rpc;

import java.lang.reflect.Method;
import java.util.Arrays;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProxyMethodConvertedToCallObject extends TraceableInfo {
	

	public ProxyMethodConvertedToCallObject(String aMessage, Object aSource, Method aMethod, Object[] args, RemoteCall aCall) {
		super(aMessage, aSource );
	}
	
	
	public static ProxyMethodConvertedToCallObject newCase(Object aSource, Method aMethod, Object[] args, RemoteCall aCall) {
    	String aMessage = aCall + "<--" + "" +aMethod + "(" + Arrays.toString(args) + ")";
		ProxyMethodConvertedToCallObject retVal = new ProxyMethodConvertedToCallObject(aMessage, aSource,  aMethod, args, aCall);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
