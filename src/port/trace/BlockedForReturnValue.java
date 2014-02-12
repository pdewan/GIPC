package port.trace;

import inputport.rpc.duplex.RPCReturnValueQueue;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
import util.trace.Tracer;
@DisplayToString(true)
public  class BlockedForReturnValue  extends TraceableInfo {
//	RPCReturnValueQueue rpcReturnValueQueue;
	public BlockedForReturnValue(String aMessage, Object aFinder)  {
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
	
	
	
	public static BlockedForReturnValue newCase(RPCReturnValueQueue aReturnValueQueue) {
    	String aMessage = "Blocked on: " + aReturnValueQueue;
    	BlockedForReturnValue retVal = new BlockedForReturnValue(aMessage, aReturnValueQueue);
    	retVal.announce();
    	return retVal;
	}
	static {
//		Tracer.setKeywordDisplayStatus(BlockedForReturnValue.class, true);
	}

}
