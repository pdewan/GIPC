package examples.gipc.consensus.paxos.scenarios;

import java.util.Collection;
import java.util.List;

import inputport.InputPort;
import inputport.datacomm.group.AnAscendingGroupSendMessageForwarder;
import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.group.object.AnAscendingMultipleSendGroupForwarder;
import inputport.rpc.SerializableCall;

public class APaxosMultiCaster<InMessageType> extends AnAscendingGroupSendMessageForwarder<InMessageType> {
	
	public static final String ACCEPTED = "accepted";
	public static final String ACCEPT = "accept";
	public static final String PREPARE = "prepare";
//	public static final String PREPARED = "prepared";

	protected InputPort inputPort;
	protected String myName;

	
	public APaxosMultiCaster(InputPort anInputPort, GroupNamingSender<InMessageType> aDestination) {
		super(aDestination);
		inputPort = anInputPort;
		myName = inputPort.getLocalName();
	}

	public static boolean isCall(Object message, String aCallName) {
		if (message instanceof SerializableCall) {
			SerializableCall aCall = (SerializableCall) message;
			return(aCall.getSerializableMethod().getMethodName().equals(aCallName)) ;
					
		}
		return false;
	}
	
	protected void sendAcceptFrom1(List<String> aSortedList, InMessageType message) {
		String aFirstElement = aSortedList.remove(0);
		// send to 1 (itself)
		destination.send(aFirstElement, message); 
		String aSecondElement = aSortedList.remove(0);
		// send to 2
		destination.send(aSecondElement, message); 
		// send to others (3)
		destination.send(aSortedList, message);		
	}
	protected void sendAcceptFrom3(List<String> aSortedList, InMessageType message) {
		String aLastElement = aSortedList.remove(aSortedList.size() -1);
		// send to 3 (itself)
		destination.send(aSortedList, message);
		// send to others (1 and 2)
		destination.send(aSortedList, message); 
	}

	
	protected void sendAccept(List<String> aSortedList, InMessageType message) {
		if (myName.equals("1")) {
			sendAcceptFrom1(aSortedList, message);
		} else if (myName.equals("3")) {
			sendAcceptFrom3(aSortedList, message);
		} else {
			destination.send(aSortedList, message);
		}	
	}
	
	// stop 2 from sending accepted to 3 here until 3 is ready to begin accept phase 
	 protected void sendAcceptedFrom2(List<String> aSortedList, InMessageType message) {
	    	String aLastElement = aSortedList.remove(aSortedList.size() -1);
	    	// send to 1 and 2
			destination.send(aSortedList, message);
			// send to 3
			destination.send(aLastElement, message); 		
		}
    protected void sendAccepted(List<String> aSortedList, InMessageType message) {
    	if (myName.equals("2")) {
    		sendAcceptedFrom2(aSortedList, message);
    	} else {
    		destination.send(aSortedList, message);
    	}	
	}
    // we do not have to really control this method
    protected void sendPrepareFrom1(List<String> aSortedList, InMessageType message) {
    	destination.send(aSortedList, message);
		
	}
    protected void sendPrepareFrom3(List<String> aSortedList, InMessageType message) {
    	
    	String aLastElement = aSortedList.remove(aSortedList.size() -1);
    	// send to 3 (itself)
		destination.send(aLastElement, message);
		// send to 1
    	String aFirstElement = aSortedList.remove(0);
		destination.send(aFirstElement, message);
		// send to others (2)
		destination.send(aSortedList, message);
		
	}
    protected void sendPreparedFrom3(List<String> aSortedList, InMessageType message) {    	
    	
		destination.send(aSortedList, message);
		
	}
    protected void sendPrepare(List<String> aSortedList, InMessageType message) {
    	if (myName.equals("1")) {
    		sendPrepareFrom1(aSortedList, message);
    	} else if (myName.equalsIgnoreCase("3")) {
    		sendPrepareFrom3(aSortedList, message);
    	} else {
    		destination.send(aSortedList, message);
    	}		
	}
    protected void sendPrepared(List<String> aSortedList, InMessageType message) {
    	if (myName.equals("3") && aSortedList.contains("3")) {
    		sendPreparedFrom3(aSortedList, message);
    
    	} else {
    		destination.send(aSortedList, message);
    	}		
	}
   
	
	public void send(Collection<String> clientNames, InMessageType message) {
		 
		 if ( destination instanceof APaxosMultiCaster ||
				 (!isCall(message, ACCEPTED) && 
					!isCall(message, ACCEPT) &&
					!isCall(message, PREPARE ) 
//					!isCall(message, PREPARED)
					)) {
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
//		else if (isCall(message, PREPARED)) {
//			sendPrepared(aSortedList, message);
//		}
	}


}
