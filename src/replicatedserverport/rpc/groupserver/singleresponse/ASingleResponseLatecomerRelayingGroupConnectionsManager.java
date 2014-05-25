package replicatedserverport.rpc.groupserver.singleresponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import port.ParticipantChoice;
import replicatedserverport.rpc.duplex.singleresponse.ControlMessage;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.relayed.latecomer.ALatecomerRelayingGroupConnectionsManager;
import util.trace.Tracer;



public class ASingleResponseLatecomerRelayingGroupConnectionsManager
	extends ALatecomerRelayingGroupConnectionsManager
	 {	
	
	List<MessageWithSource>  messagesReceivedInReplayMode = new ArrayList();
	
	Set<String> controlMessageBasedConnections = new HashSet();
	
	public ASingleResponseLatecomerRelayingGroupConnectionsManager(GroupSessionPort<Object> aGroupSessionPort, ParticipantChoice aChoice) {
		super (aGroupSessionPort, aChoice);
	}
	
	
	// will be overridden by single response connection manager
	protected void processPastMessage (String remoteClientName,
			Object message) {
		// sending control message could cause latecomer sever to get confused
		Tracer.info(this, "Past message:" + message);
		if (!(message instanceof ControlMessage)) {		
			super.processPastMessage(remoteClientName, message);
		} 
		else {
			if (outstandingCallsInReplayMode > 0) {
//				lastMessageWaitedFor = message;
				Tracer.info(this, "waiting to process control message " + message + " before previous call finishes");
				doWait(); // give calls the right environment
			}
			Tracer.info(this, "Adding connecton:" + remoteClientName );
			controlMessageBasedConnections.add(remoteClientName);
		}
	}
	protected boolean preSend(Object message) {
		return super.preSend(message) 
				|| message instanceof ControlMessage // want client to tell server about status
				|| 	joinChoice != ParticipantChoice.CLIENT_ONLY; // want server to update clients at least in the replicated case

//	@Override
//	public void send(String remoteEnd, Object message) {
////		// in replay mode, no one waiting for return value
////		if (numOutstandingFunctionCallsInReplayMode > 0 && 
////				FunctionReturnValueDeterminerSelector.isReturnValue(message)) {
////			numOutstandingFunctionCallsInReplayMode--;
////			return;
////		}
//		if (!replayMode || message instanceof ControlMessage)
//		super.send(remoteEnd, message);
//
//	}
//	@Override
//	public void send(Set<String> clientNames, Object message) {
//		if (!replayMode ||  message instanceof ControlMessage) // control message is also not sent!
//
//		super.send(clientNames, message);
//	}
//
//	@Override
//	public void sendOthers(Object message) {
//		if (!replayMode ||  message instanceof ControlMessage)
//
//		super.sendOthers(message);
//		
	}
	
	@Override
	public Set<String> getRemoteEndPoints() {
		if (replayMode && joinChoice == ParticipantChoice.SERVER_ONLY) { // receives control messages 
//		return super.getRemoteEndPoints();
			Tracer.info(this, "returning control based connections" + controlMessageBasedConnections );
			return controlMessageBasedConnections; // honor history
	}else
			return super.getRemoteEndPoints();
	}
//	// incase some action has to be taken as in subclass
//		protected synchronized void replayModeStarted() {
//			currentMessagesReceivedInReplayMode =
//		}
		// incase some action has to be taken as in subclass

	    protected synchronized void replayModeEnded() {
	    	super.replayModeEnded();
	    	for (MessageWithSource message:messagesReceivedInReplayMode) {
				Tracer.info(this, "Playing buffered  message " + message + " after replay mode finish");

	    		super.messageReceived(message.getSource(), message.getMessage());
	    	}
			
		}
	
	// must make sure that this is mutually exlusive with late comer messages
		// this synchronized may cause deadlock
		// the control message a client sends can be receibed before the latecomer messges are received
		// as latecomer server notifies observers before returning value
		// do not know how to fix this problem if we are using rpc
		// this is why we need the server message manager to keeo track of late comer status
		// could buffer messages received until latecomer has been updated
		// we do have the information
		// we do not need to make this methiod synchronized in that case
		@Override
		public synchronized void messageReceived(String remoteClientName,
				Object message) {
			
			if (message instanceof MessageWithSource) { // current message
				// do not want to buffer return values as these might be needed to complete
				// current call and get out of replay mode
				MessageWithSource messageWithSource = (MessageWithSource) message;
				
				if (replayMode  ) {
					Tracer.info (this, "Buffering message in replay mode:" + message);
					messagesReceivedInReplayMode.add((MessageWithSource) message);
				} else
					super.messageReceived(remoteClientName, message);
			} else { // past message
				super.messageReceived(remoteClientName, message);

			}
		}

	
	
}
