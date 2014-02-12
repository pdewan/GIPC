package inputport.rpc.duplex;

import inputport.datacomm.duplex.DuplexInputPort;
import inputport.rpc.RPCRegistry;
import inputport.rpc.ReceivedCallInvoker;
import inputport.rpc.DuplexReceivedCallInvokerFactory;

public class SynchronousDuplexReceivedCallInvokerSelector {
	static DuplexReceivedCallInvokerFactory receivedCallInvokerFactory; 
	static DuplexReceivedCallInvoker createDuplexReceivedCallInvoker(
			LocalRemoteReferenceTranslator aRemoteHandler, DuplexInputPort<Object> aReplier, RPCRegistry anRPCRegistry) {
		return receivedCallInvokerFactory.createDuplexReceivedCallInvoker(aRemoteHandler, aReplier, anRPCRegistry);
	}
	public static DuplexReceivedCallInvokerFactory getReceivedCallInvokerFactory() {
		return receivedCallInvokerFactory;
	}
	public static void setReceivedCallInvokerFactory(
			DuplexReceivedCallInvokerFactory receivedCallInvokerFactory) {
		SynchronousDuplexReceivedCallInvokerSelector.receivedCallInvokerFactory = receivedCallInvokerFactory;
	}

}
