package trace.port.rpc;

import inputport.rpc.duplex.RPCReturnValueQueue;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
public  class RemoteCallBlockedForReturnValue  extends TraceableInfo {
//	RPCReturnValueQueue rpcReturnValueQueue;
	public RemoteCallBlockedForReturnValue(String aMessage, Object aFinder, RPCReturnValueQueue aQueue)  {
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
	
	
	
	public static RemoteCallBlockedForReturnValue newCase(Object aFinder, RPCReturnValueQueue aReturnValueQueue) {
    	String aMessage = "?<-" + aReturnValueQueue;
    	RemoteCallBlockedForReturnValue retVal = new RemoteCallBlockedForReturnValue(aMessage, aFinder, aReturnValueQueue);
    	retVal.announce();
    	return retVal;
	}
	static {
//		Tracer.setKeywordDisplayStatus(BlockedForReturnValue.class, true);
	}

}
