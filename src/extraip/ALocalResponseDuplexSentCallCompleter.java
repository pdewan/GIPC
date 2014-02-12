package extraip;

import inputport.InputPort;
import inputport.rpc.duplex.ADuplexSentCallCompleter;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
import inputport.rpc.duplex.RPCReturnValueQueue;
import replicatedserverport.rpc.duplex.fixedresponse.ClientServerMapping;

public class ALocalResponseDuplexSentCallCompleter extends ADuplexSentCallCompleter {
	public ALocalResponseDuplexSentCallCompleter(InputPort anInputPort, LocalRemoteReferenceTranslator aRemoteHandler) {
		super(anInputPort, aRemoteHandler);
	}
	protected void createRPCReturnValueReceiver(String aRemoteEnd) {
//		if (!aRemoteEnd.equals(inputPort.getLocalName()))
//			return;		
		if (!ClientServerMapping.isServer(inputPort.getLocalName(), aRemoteEnd))
			return;
		RPCReturnValueQueue returnValueReceiver = createRPCReturnValueReceiver(localRemoteReferenceTranslator, null);
//			nameToRPCReturnValueReceiver.put(aRemoteEnd, returnValueReceiver);
		nameToRPCReturnValueReceiver.put(aRemoteEnd, returnValueReceiver);
//		System.out.println("Created return value for:" + logicalName );
//
//		if (inputPort instanceof ClientInputPort) { // takes care of replicated  fault tolerand ports
//			nameToRPCReturnValueReceiver.put(((ClientInputPort) inputPort).getLogicalRemoteEndPoint(), returnValueReceiver);
//		} else {
//			nameToRPCReturnValueReceiver.put(aRemoteEnd, returnValueReceiver);
//		}
	}

}
