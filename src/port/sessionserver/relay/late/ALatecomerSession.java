package port.sessionserver.relay.late;

import java.util.ArrayList;
import java.util.List;

import port.sessionserver.ASession;

import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public class ALatecomerSession extends ASession implements LatecomerSession {
	List<MessageWithSource> messages = new ArrayList();
	List<MessageWithSource> serverMessages = new ArrayList();
	List<MessageWithSource> clientMessages = new ArrayList();
	@Override
	public void addMessage(MessageWithSource message) {
		messages.add(message);
	}
	@Override
	public List<MessageWithSource> getMessages() {
		return messages;
	}


	@Override
	public void clearMessages() {
		messages.clear();
	}

	@Override
	public List<MessageWithSource> getServerMessages() {
		return serverMessages;
	}
	
	@Override
	public void addServerMessage(MessageWithSource message) {
		serverMessages.add(message);
	}

	@Override
	public void clearServerMessages() {
		serverMessages.clear();
	}

	@Override
	public List<MessageWithSource> getClientMessages() {
		return clientMessages;
	}
	
	@Override
	public void addClientMessage(MessageWithSource message) {
		clientMessages.add(message);
	}

	@Override
	public void clearClientMessages() {
		clientMessages.clear();
	}

}
