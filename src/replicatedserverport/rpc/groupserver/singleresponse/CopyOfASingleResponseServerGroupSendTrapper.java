package replicatedserverport.rpc.groupserver.singleresponse;

import inputport.InputPort;
import inputport.datacomm.group.AnAbstractGroupSendTrapper;
import inputport.datacomm.group.GroupNamingSender;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import replicatedserverport.rpc.duplex.singleresponse.BufferedMessageSender;
import replicatedserverport.rpc.duplex.singleresponse.MessageWithId;
import replicatedserverport.rpc.duplex.singleresponse.ServerMessagesManager;
import util.trace.Tracer;


public class CopyOfASingleResponseServerGroupSendTrapper extends AnAbstractGroupSendTrapper<Object, Object>
	implements BufferedMessageSender {
	InputPort inputPort;
	ServerMessagesManager sentMessagesManager;
	public CopyOfASingleResponseServerGroupSendTrapper(InputPort anInputPort, GroupNamingSender<Object> destination, ServerMessagesManager aSentMessagesManager) {
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
	
	@Override
	public synchronized void send(Collection<String> clientNames, Object aMessage) {
		boolean isCurrentServer = false;
		for (String clientName:clientNames) {
			if (!(sentMessagesManager.shouldSend(inputPort.getLocalName(), clientName))) {
				Tracer.info(this, "Not sending value as not current server");
				continue;	
			}
			isCurrentServer = true;
			List<MessageWithId> bufferedMessages = sentMessagesManager.getBufferedMessages(clientName);
			if (bufferedMessages.size() == 0) continue;
			Set<String> clientNameSet = new HashSet();
			clientNameSet.add(clientName);
			for (int i = 0; i < bufferedMessages.size(); i++) {
				MessageWithId messageWithID = bufferedMessages.get(i);
				Tracer.info(this, "sending buffered message " +  messageWithID.getMessage());
				destination.send (clientNameSet, messageWithID.getMessage());
			}
//			sentMessagesManager.addMessage(clientName, aMessage); // so that count is updated, could be better
//			bufferedMessages.clear(); // we are going to send it nxt
		}
		for (String clientName:clientNames) {
			sentMessagesManager.addMessage(clientName, aMessage); // record the id
		}
		if (!isCurrentServer)
			return;
		Tracer.info(this, "sending message " + aMessage + " to clientNames");
		destination.send(clientNames, aMessage);
		Tracer.info(this, "sent message " + aMessage + " to clientNames");

		for (String clientName:clientNames) {
			Tracer.info (this, "Clearing buffer for " + clientName);
			sentMessagesManager.getBufferedMessages(clientName).clear();
		}
		
	}


	@Override
	public void sendBufferedMessage(String clientName, Object message) {
		Set<String> clientNames = new HashSet();
		clientNames.add(clientName);
		destination.send(clientNames, message);
	} 
	


}
