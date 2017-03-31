package inputport.rpc.group;

import inputport.InputPort;
import inputport.rpc.RemoteCall;
import inputport.rpc.duplex.ADuplexSentCallCompleter;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
import inputport.rpc.duplex.RPCReturnValueQueue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public abstract class AGroupAbstractReturnerOfValueOfRemoteFunctionCall 
	extends ADuplexSentCallCompleter
	implements GroupMapReturnerOfValueOfRemoteFunctionCall {

	public AGroupAbstractReturnerOfValueOfRemoteFunctionCall(InputPort anInputPort, LocalRemoteReferenceTranslator aLocatRemoteReferenceTranslator) {
		super(anInputPort, aLocatRemoteReferenceTranslator);
	}
	protected Map<String, Object> returnValueOfRemoteFunctionCallAsMap(Set<String> clientNames, RemoteCall aSerializableCall) {
		Map<String, Object> retVal = new HashMap<>();
		RPCReturnValueQueue[] rpcReturnValueReceivers = null;
		rpcReturnValueReceivers = new RPCReturnValueQueue[clientNames.size()];
		for (String aClientName: clientNames) {
		
			RPCReturnValueQueue rpcReturnValueReceiver = getRPCReturnValueReceiver(
					aClientName);
			retVal.put(aClientName, rpcReturnValueReceiver.takeReturnValue());

		}
		
		return retVal;
	
	
	}

}
