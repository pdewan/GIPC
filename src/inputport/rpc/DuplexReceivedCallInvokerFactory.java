package inputport.rpc;

import inputport.datacomm.duplex.DuplexInputPort;
import inputport.rpc.duplex.DuplexReceivedCallInvoker;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;

public interface DuplexReceivedCallInvokerFactory {
	DuplexReceivedCallInvoker createDuplexReceivedCallInvoker(
			LocalRemoteReferenceTranslator aRemoteHandler,DuplexInputPort<Object> aReplier, RPCRegistry anRPCRegistry);

}
