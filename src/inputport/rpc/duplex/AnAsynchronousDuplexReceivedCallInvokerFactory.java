package inputport.rpc.duplex;

import inputport.datacomm.duplex.DuplexInputPort;
import inputport.rpc.RPCRegistry;
import inputport.rpc.DuplexReceivedCallInvokerFactory;

public class AnAsynchronousDuplexReceivedCallInvokerFactory extends ADuplexReceivedCallInvokerFactory implements DuplexReceivedCallInvokerFactory{

	@Override
	public DuplexReceivedCallInvoker createDuplexReceivedCallInvoker(
			LocalRemoteReferenceTranslator aRemoteHandler,
			DuplexInputPort<Object> aReplier, RPCRegistry anRPCRegistry) {
//		DuplexReceivedCallInvoker synchronousReceivedCallInvoker = super.createReceivedCallInvoker(aRemoteHandler, aReplier, anRPCRegistry);
		DuplexReceivedCallInvoker synchronousReceivedCallInvoker = SynchronousDuplexReceivedCallInvokerSelector.createDuplexReceivedCallInvoker(aRemoteHandler, aReplier, anRPCRegistry);

//		return new AnAsynchronousDuplexReceivedCallInvoker(aRemoteHandler, aReplier, anRPCRegistry);
		return new AnAsynchronousSingleThreadDuplexReceivedCallInvoker(synchronousReceivedCallInvoker);
	}

}
