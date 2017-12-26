package trace.port.rpc;

import inputport.rpc.duplex.RPCReturnValueQueue;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
public  class RemoteCallReturnValueDetermined  extends TraceableInfo {
//	RPCReturnValueQueue rpcReturnValueQueue;
	public RemoteCallReturnValueDetermined(String aMessage, Object aFinder, Object aCall, Object aRetVal)  {
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
	
	
	
	public static RemoteCallReturnValueDetermined newCase(Object aFinder, Object aCall, Object aRetVal) {
    	String aMessage = aCall + ":" + aRetVal ;
    	RemoteCallReturnValueDetermined retVal = new RemoteCallReturnValueDetermined(aMessage, aFinder, aCall,  aRetVal);
    	retVal.announce();
    	return retVal;
	}
	static {
//		Tracer.setKeywordDisplayStatus(BlockedForReturnValue.class, true);
	}

}
