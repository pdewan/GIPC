package inputport.rpc.group;

import java.util.Map;
import java.util.Set;

import inputport.InputPort;
import inputport.rpc.RemoteCall;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;


public  class AGroupMapReturnerOfValueOfRemoteFunctionCall 
	extends AGroupAbstractReturnerOfValueOfRemoteFunctionCall
	implements GroupMapReturnerOfValueOfRemoteFunctionCall {

	public AGroupMapReturnerOfValueOfRemoteFunctionCall(InputPort anInputPort, LocalRemoteReferenceTranslator aLocatRemoteReferenceTranslator) {
		super(anInputPort, aLocatRemoteReferenceTranslator);
	}
	
	public Map<String, Object> returnValueOfRemoteFunctionCall(Set<String> clientNames, RemoteCall aSerializableCall) {
		return returnValueOfRemoteFunctionCallAsMap(clientNames, aSerializableCall);
		//		Map<String, Object> retVal = new HashMap<>();
//		RPCReturnValueQueue[] rpcReturnValueReceivers = null;
//		rpcReturnValueReceivers = new RPCReturnValueQueue[clientNames.size()];
//		for (String aClientName: clientNames) {
//		
//			RPCReturnValueQueue rpcReturnValueReceiver = getRPCReturnValueReceiver(
//					aClientName);
//			retVal.put(aClientName, rpcReturnValueReceiver.takeReturnValue());
//
//		}
//		
//		return retVal;
	
	
	}

}
