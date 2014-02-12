package inputport.rpc.group;

import inputport.InputPort;
import inputport.rpc.RemoteCall;
import inputport.rpc.duplex.ADuplexSentCallCompleter;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
import inputport.rpc.duplex.RPCReturnValueQueue;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;


public class AGroupReturnerOfValueOfRemoteFunctionCall 
	extends ADuplexSentCallCompleter
	implements GroupReturnerOfValueOfRemoteFunctionCall {

	public AGroupReturnerOfValueOfRemoteFunctionCall(InputPort anInputPort, LocalRemoteReferenceTranslator aLocatRemoteReferenceTranslator) {
		super(anInputPort, aLocatRemoteReferenceTranslator);
	}
	public Object[] returnValueOfRemoteFunctionCall(Set<String> clientNames, RemoteCall aSerializableCall) {
		Object[] retVal = new Serializable[clientNames.size()];
		if (clientNames.size() == 0) return  retVal;
		RPCReturnValueQueue[] rpcReturnValueReceivers = null;
		rpcReturnValueReceivers = new RPCReturnValueQueue[clientNames.size()];
		Iterator<String> clientNameIterator = clientNames.iterator();
		for (int i = 0; i < clientNames.size(); i++) {
			rpcReturnValueReceivers[i] = getRPCReturnValueReceiver(
					clientNameIterator.next());

		}
		for (int i = 0; i < clientNames.size(); i++) {
			try {
//				System.out.println("Taking return value from " + rpcReturnValueReceivers[i]);

				retVal[i] = rpcReturnValueReceivers[i].takeReturnValue();
//				System.out.println("Took return value");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	return retVal;
	
	}

}
