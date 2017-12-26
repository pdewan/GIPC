package trace.port.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProxyCreated extends TraceableInfo {
	

	public ProxyCreated(String aMessage, Object aSource, Object aProxy, Class[] aInterfaces, InvocationHandler anInvocationHandler) {
		super(aMessage, aSource );
	}
	
	
	public static ProxyCreated newCase(Object aSource,  Object aProxy, Class[] anInterfaces, InvocationHandler anInvocationHandler) {
    	String aMessage =  
    			aProxy.getClass().getSimpleName() +
  
    		   ":" + Arrays.toString(anInterfaces) + "->" +anInvocationHandler;
    	ProxyCreated retVal = new ProxyCreated(aMessage, aSource, aProxy, anInterfaces, anInvocationHandler);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
