package replicatedserverport.rpc.duplex.singleresponse;

import inputport.ClientInputPort;
import inputport.InputPort;
import inputport.rpc.duplex.ADuplexSentCallCompleter;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
import inputport.rpc.duplex.RPCReturnValueQueue;

public class ASingleResponseDuplexSentCallCompleter extends ADuplexSentCallCompleter {
	String logicalName;
	public ASingleResponseDuplexSentCallCompleter(InputPort anInputPort, LocalRemoteReferenceTranslator aRemoteHandler) {
		super(anInputPort, aRemoteHandler);
		logicalName = ((ClientInputPort) anInputPort).getLogicalRemoteEndPoint();
	}
	// this can probably be the normal call completer,
	// the logical name will be the same as rmeote end
	// whhen not replicated
	// or it will be null, so the call completer could
	// look at that
	protected void createRPCReturnValueReceiver(String aRemoteEnd) {
		// we need to create only one receiver, though we will get multiple connects
		if (nameToRPCReturnValueReceiver.get(logicalName) != null)
			return;			
		RPCReturnValueQueue returnValueReceiver = createRPCReturnValueReceiver(localRemoteReferenceTranslator, null);
//			nameToRPCReturnValueReceiver.put(aRemoteEnd, returnValueReceiver);
		nameToRPCReturnValueReceiver.put(logicalName, returnValueReceiver);
//		System.out.println("Created return value for:" + logicalName );
//
//		if (inputPort instanceof ClientInputPort) { // takes care of replicated  fault tolerand ports
//			nameToRPCReturnValueReceiver.put(((ClientInputPort) inputPort).getLogicalRemoteEndPoint(), returnValueReceiver);
//		} else {
//			nameToRPCReturnValueReceiver.put(aRemoteEnd, returnValueReceiver);
//		}
	}

}
