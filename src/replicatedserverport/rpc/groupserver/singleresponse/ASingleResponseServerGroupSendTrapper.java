package replicatedserverport.rpc.groupserver.singleresponse;

import inputport.InputPort;
import inputport.datacomm.group.AnAbstractGroupSendTrapper;
import inputport.datacomm.group.GroupNamingSender;

import java.util.HashSet;
import java.util.Set;

import replicatedserverport.rpc.duplex.singleresponse.ASingleResponseServerDuplexSendTrapper;
import replicatedserverport.rpc.duplex.singleresponse.BufferedMessageSender;
import util.trace.Tracer;


public class ASingleResponseServerGroupSendTrapper extends AnAbstractGroupSendTrapper<Object, Object>
	implements BufferedMessageSender {
	InputPort inputPort;
	GroupServerMessagesManager sentMessagesManager;
	public ASingleResponseServerGroupSendTrapper(InputPort anInputPort, GroupNamingSender<Object> destination, GroupServerMessagesManager aSentMessagesManager) {
		super(destination);
		inputPort = anInputPort;
		sentMessagesManager = aSentMessagesManager;	
	}

	
//	@Override
//	public void send(Set<String> clientNames, Object aMessage) {
//		boolean isCurrentServer = false;
//		for (String clientName:clientNames) {
//			if (!(sentMessagesManager.shouldSend(inputPort.getLocalName(), clientName))) {
//				sentMessagesManager.addMessage(clientName, aMessage);
//				continue;	
//			}
//			isCurrentServer = true;
//			List<MessageWithId> bufferedMessages = sentMessagesManager.getBufferedMessages(clientName);
//			if (bufferedMessages.size() == 0) continue;
//			Set<String> clientNameSet = new HashSet();
//			clientNameSet.add(clientName);
//			for (int i = 0; i < bufferedMessages.size(); i++) {
//				MessageWithId messageWithID = bufferedMessages.get(i);
//				destination.send (clientNameSet, messageWithID.getMessage());
//			}
//			sentMessagesManager.addMessage(clientName, aMessage); // so that count is updated, could be better
//			bufferedMessages.clear(); // we are going to send it nxt
//		}
//		if (!isCurrentServer)
//			return;
//		destination.send(clientNames, aMessage);			
//	} 
	// synchronized as we do not want new message to corrupt previous messages
	
//	@Override
//	public synchronized void send(Set<String> clientNames, Object aMessage) {
//		for (String clientName:clientNames) {
//			sentMessagesManager.addMessage(clientName, aMessage); // record the id and for secondary servers, add the message
////			sentMessagesManager.incrementMessageCount(clientName); // record the id
//
//		}
//		for (String clientName:clientNames) {
//			ASingleResponseServerDuplexSendTrapper.sendBufferedMessages(clientName,  inputPort, sentMessagesManager);
//		}		
//	}
	// what is this is a serializable call with a return value
	// when replaying messages, no return value should be sent
	// maybe value returning calls should not be replayed or return values should not be sent
	// in latecomer mode this does not make sense as the messages may already have been sent
	public synchronized void send(Set<String> clientNames, Object aMessage) {
		if (sentMessagesManager.isServerForAllClients(inputPort.getLocalName()) &&  // will return true if no control message received
//				!sentMessagesManager.isLatecomerBeforeBuffering())
			!sentMessagesManager.isLatecomerBasedOnReplayMode()) // check this here for sure as we dont wnat to send to null clients
			groupSendWithoutBuffering(clientNames, aMessage);
		else {
			bufferAndThenSendIndvidually(clientNames, aMessage);
		}

	}
	void groupSendWithoutBuffering(Set<String> clientNames, Object aMessage) {
		// because this is sent without buffering, if this client goes ahead of theprimary
		// server and then has to replay, the replay will go to individual users
		// and thus not be logged.
		// This means a latecomer will not get the logged message.
		// Maybe the right model is to have a group message log
		// which is asociated with the user to whom the message should not go
		// This log would hve to be integrated with the indivudual log
		for (String clientName:clientNames) {
//			sentMessagesManager.addMessage(clientName, aMessage); // record the id and for secondary servers, buffer the message
			sentMessagesManager.incrementMessageCount(clientName); // record the id, do this only if not latecomer even though downstream object will fildter out messages

		}
		// this version will go to the relayer as a group message
		// if no relayer, individual sends will fail, but then this server
		// is dead, so not buffering is not an issue
		Tracer.info(this, "Up to date server sending to " + clientNames + " non bufffered group message:" + aMessage + " which willbe logged by a latecomer server");
//		Tracer.info((this, "Up to date server sending to " + clientNames + " non bufffered group message:" + aMessage + " which willbe logged by a latecomer server"));
		destination.send(clientNames, aMessage);
//		for (String clientName:clientNames) {
//			ASingleResponseServerDuplexSendTrapper.sendBufferedMessages(clientName,  inputPort, sentMessagesManager);
//		}		
	}
	
	void bufferAndThenSendIndvidually(Set<String> clientNames, Object aMessage) {
		sentMessagesManager.addGroupMessage(aMessage); // may be deleted now or later
		for (String clientName:clientNames) {
			sentMessagesManager.addMessage(clientName, aMessage); // record the id and for secondary servers, buffer the message
//			sentMessagesManager.incrementMessageCount(clientName); // record the id

		}
		
		for (String clientName:clientNames) {
			ASingleResponseServerDuplexSendTrapper.sendBufferedMessages(clientName,  inputPort, sentMessagesManager);
//			sendBufferedMessages(clientName,  inputPort, sentMessagesManager);

		}		
	}
//	public  void sendBufferedMessages(String remoteEnd, InputPort inputPort, ServerMessagesManager serverSentMessagesManager) {
//		ASingleResponseServerDuplexSendTrapper.sendBufferedMessages(remoteEnd,  inputPort, sentMessagesManager);
//
//	}
	@Override
	public synchronized void send(String clientName, Object aMessage) {
			sentMessagesManager.addMessage(clientName, aMessage); // record the id		
//			ASingleResponseServerDuplexSendTrapper.sendBufferedMessages(clientName,  inputPort, sentMessagesManager);
			ASingleResponseServerDuplexSendTrapper.sendBufferedMessages(clientName,  inputPort, sentMessagesManager);

				
	}
//	@Override
//	public synchronized void send(Set<String> clientNames, Object aMessage) {
//		boolean isCurrentServer = false;
//		for (String clientName:clientNames) {
//			if (!(sentMessagesManager.shouldSend(inputPort.getLocalName(), clientName))) {
//				Tracer.info(this, "Not sending value as not current server");
//				continue;	
//			}
//			isCurrentServer = true;
//			List<MessageWithId> bufferedMessages = sentMessagesManager.getBufferedMessages(clientName);
//			if (bufferedMessages.size() == 0) continue;
////			Set<String> clientNameSet = new HashSet();
////			clientNameSet.add(clientName);
//			for (int i = 0; i < bufferedMessages.size(); i++) {
//				MessageWithId messageWithID = bufferedMessages.get(i);
//				Tracer.info(this, "sending buffered message " +  messageWithID.getMessage());
//				sendBufferedMessage(clientName,  messageWithID.getMessage());
////				destination.send (clientNameSet, messageWithID.getMessage());
//			}
////			sentMessagesManager.addMessage(clientName, aMessage); // so that count is updated, could be better
////			bufferedMessages.clear(); // we are going to send it nxt
//		}
//		for (String clientName:clientNames) {
//			sentMessagesManager.addMessage(clientName, aMessage); // record the id
//		}
//		if (!isCurrentServer)
//			return;
//		Tracer.info(this, "sending message " + aMessage + " to clientNames");
//		destination.send(clientNames, aMessage);
//		Tracer.info(this, "sent message " + aMessage + " to clientNames");
//
//		for (String clientName:clientNames) {
//			Tracer.info (this, "Clearing buffer for " + clientName);
//			sentMessagesManager.getBufferedMessages(clientName).clear();
//		}
//		
//	}


//	@Override
//	public void sendBufferedMessage(String clientName, Object message) {
//		Set<String> clientNames = new HashSet();
//		clientNames.add(clientName);
//		destination.send(clientNames, message);
//	} 
	
	// why call it sendBufferedMessage rater than simply send? because group trapper did not
	// have a normal send before?
	// becauuse we do not want the message recorded
	public void sendBufferedMessage(String clientName, Object message) {
//		Set<String> clientNames = new HashSet();
//		clientNames.add(clientName);
		if (sentMessagesManager.isGroupMessage(message)) {
			Tracer.info(this, "Sending buffered group message:" + message);
			Set<String> clientNames = new HashSet();
			clientNames.add(clientName);
			destination.send(clientNames, message); // log this message in the latecomer server
			sentMessagesManager.removeGroupMessage(message); // we want it logged a single time
			Tracer.info(this, "Removed buffered group message:" + message);

		}
		else	{	
			Tracer.info(this, "Sending individual buffered  message:" + message);
			destination.send(clientName, message); // if connected to latecomer, individual message should not be logged
		}
	} 

	
	

}
