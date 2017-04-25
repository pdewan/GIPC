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
	
	public static final String ACCEPTED = "accepted";
	public static final String ACCEPT = "accept";
	public static final String PREPARE = "prepare";

	
	public APaxosMultiCaster(GroupNamingSender<InMessageType> aDestination) {
		super(aDestination);
	}

	public static boolean isCall(Object message, String aCallName) {
		if (message instanceof SerializableCall) {
			SerializableCall aCall = (SerializableCall) message;
			return(aCall.getSerializableMethod().getMethodName().equals(aCallName)) ;
					
		}
		return false;
	}
	
	protected void sendAccept(List<String> aSortedList, InMessageType message) {
		String aLastElement = aSortedList.remove(aSortedList.size() -1);
		destination.send(aSortedList, message);
		// stop 1 from sending accept to 3 until 3's prepare request is executed at all sites		
		destination.send(aLastElement, message); 		
	}
    protected void sendAccepted(List<String> aSortedList, InMessageType message) {
    	String aLastElement = aSortedList.remove(aSortedList.size() -1);
		destination.send(aSortedList, message);
		// stop 2 from sending accepted to 3 here until 3 is ready to begin accept phase 
		destination.send(aLastElement, message); 	
		
	}
    protected void sendPrepare(List<String> aSortedList, InMessageType message) {
    	String aFirstElement = aSortedList.remove(0);
    	String aLastElement = aSortedList.remove(aSortedList.size() -1);
		destination.send(aSortedList, message);
		// stop 3 from sending prepare request to 1 until 1 has sent accept to others
		destination.send(aFirstElement, message); 
		destination.send(aLastElement, message);
		
	}
   
	
	public void send(Collection<String> clientNames, InMessageType message) {
		 
		 if ( destination instanceof APaxosMultiCaster ||
				 (!isCall(message, ACCEPTED) && 
					!isCall(message, ACCEPT) &&
					!isCall(message, PREPARE ))) {
			 super.send(clientNames, message);
			 return;
		}
		
		List<String> aSortedList = AnAscendingMultipleSendGroupForwarder.sort(clientNames);
		if (aSortedList.size() == 0) {
			super.send(clientNames, message);
		}
		if (isCall(message, ACCEPT)) {
			sendAccept(aSortedList, message);
		} else if (isCall(message, ACCEPTED)) {
			sendAccepted(aSortedList, message);
		} else if (isCall(message, PREPARE)) {
			sendPrepare(aSortedList, message);
		}
//		String aLastElement = aSortedList.remove(aSortedList.size() -1);
//		destination.send(aSortedList, message);
//		if (isCall(message, ACCEPT)) {
//			destination.send(aLastElement, message); // stop 1 until 3's prepare request is executed
//		} else {
//			destination.send(aLastElement, message); // stop 2 here until 3 is ready to begin accept phase 
//		}
	}


}
