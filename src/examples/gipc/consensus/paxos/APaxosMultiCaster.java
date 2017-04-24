package examples.gipc.consensus.paxos;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import util.trace.Tracer;
import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.group.AnAscendingGroupSendMessageForwarder;
import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.group.object.AnAscendingMultipleSendGroupForwarder;
import inputport.rpc.SerializableCall;

public class APaxosMultiCaster<InMessageType> extends AnAscendingGroupSendMessageForwarder<InMessageType> {

	
	public APaxosMultiCaster(GroupNamingSender<InMessageType> aDestination) {
		super(aDestination);
		// TODO Auto-generated constructor stub
	}
	protected boolean isAcceptRequest(Object message) {
		if (message instanceof SerializableCall) {
			SerializableCall aCall = (SerializableCall) message;
			return(aCall.getSerializableMethod().getMethodName().equals("accept")) ;
					
		}
		return false;
	}
	public void send(Collection<String> clientNames, InMessageType message) {
		 if (!isAcceptRequest(message)) {
			 super.send(clientNames, message);
			 return;
		}
		List<String> aSortedList = AnAscendingMultipleSendGroupForwarder.sort(clientNames);
		String aFirstElement = aSortedList.remove(1);
		destination.send(aFirstElement, message);
		// send accept messages to 1 and 3
		for (int i = aSortedList.size() -1; i>= 0; i--) {
			super.send(aSortedList.get(i), message);
		}
	}
//	@Override
//	public void send(Collection<String> clientNames, Object message) {
//		if (!isAcceptRequest(message)) {
//			 super.send(clientNames, message);
//			 return;
//		}
//		List<String> aSortedList = AnAscendingMultipleSendGroupForwarder.sort(clientNames);
//		String aFirstElement = aSortedList.remove(1);
//		destination.send(aFirstElement, message);
//		// send accept messages to 1 and 3
//		for (int i = aSortedList.size() -1; i>= 0; i--) {
//			super.send(aSortedList.get(i), message);
//		}
//	}

}
