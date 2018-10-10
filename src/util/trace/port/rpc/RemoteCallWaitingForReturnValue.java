package util.trace.port.rpc;

import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
public  class RemoteCallWaitingForReturnValue  extends TraceableInfo {
//	RPCReturnValueQueue rpcReturnValueQueue;
	public RemoteCallWaitingForReturnValue(String aMessage, Object aFinder)  {
		super(aMessage, aFinder );
//		rpcReturnValueQueue = aReturnValueQueue;
		
	}
	
//	
//	public Object getDestination() {
//		return  getFinder();
//	}
//	
	
//	public RPCReturnValueQueue getRPCReturnValueQueue() {
//		return rpcReturnValueQueue;
//	}
	
	
	
	public static RemoteCallWaitingForReturnValue newCase(Object aFinder) {
    	String aMessage = "";
    	RemoteCallWaitingForReturnValue retVal = new RemoteCallWaitingForReturnValue(aMessage, aFinder);
    	retVal.announce();
    	return retVal;
	}
	static {
//		Tracer.setKeywordDisplayStatus(BlockedForReturnValue.class, true);
	}

}
