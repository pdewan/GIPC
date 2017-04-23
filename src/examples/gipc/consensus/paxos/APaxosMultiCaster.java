package examples.gipc.consensus.paxos;

import java.util.List;
import java.util.Set;

import util.trace.Tracer;
import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.group.AnAscendingMultipleSendGroupForwarder;
import inputport.rpc.SerializableCall;

public class APaxosMultiCaster extends AnAscendingMultipleSendGroupForwarder {

	public APaxosMultiCaster(InputPort anInputPort,
			NamingSender<Object> aDestination) {
		super(anInputPort, aDestination);
	}
	protected boolean isAcceptRequest(Object message) {
		if (message instanceof SerializableCall) {
			SerializableCall aCall = (SerializableCall) message;
			return(aCall.getSerializableMethod().getMethodName().equals("accept")) ;
					
		}
		return false;
	}
	@Override
	public void send(Set<String> clientNames, Object message) {
		if (!isAcceptRequest(message)) {
			 super.send(clientNames, message);
			 return;
		}
		List<String> aSortedList = AnAscendingMultipleSendGroupForwarder.sort(clientNames);
		String aFirstElement = aSortedList.remove(1);
		destination.send(aFirstElement, message);
		// send accept messages to 1 and 3
		for (int i = aSortedList.size() -1; i>= 0; i--) {
			destination.send(aSortedList.get(i), message);
		}
	}

}
