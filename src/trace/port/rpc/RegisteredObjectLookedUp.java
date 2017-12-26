package trace.port.rpc;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class RegisteredObjectLookedUp extends TraceableInfo {
	

	public RegisteredObjectLookedUp(String aMessage, Object aSource, Object aKey, Object aValue) {
		super(aMessage, aSource );
	}
	
	
	public static RegisteredObjectLookedUp newCase(Object aSource, Object aKey, Object aValue) {
    	String aMessage =  aKey+ "->" + aValue;
    	RegisteredObjectLookedUp retVal = new RegisteredObjectLookedUp(aMessage, aSource, aKey, aValue);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
