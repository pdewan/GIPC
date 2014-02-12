package port.sessionserver.relay.late;

import java.util.List;

import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public class ALatecomerMessages  implements LatecomerMessages {
	List<MessageWithSource> messages;
	List<MessageWithSource> serverMessages;
	List<MessageWithSource> clientMessages;

	
	public ALatecomerMessages() {
		
	}
	public ALatecomerMessages( List<MessageWithSource> aMessageList, 
			List <MessageWithSource> aServerMessageList, 
			List <MessageWithSource> aClientMessageList) {
		messages = aMessageList;
		serverMessages = aServerMessageList;
		clientMessages = aClientMessageList;
		
	}
	public List<MessageWithSource> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageWithSource> newVal) {
		this.messages = newVal;
	}
	@Override
	public List<MessageWithSource> getServerMessages() {
		// TODO Auto-generated method stub
		return serverMessages;
	}
	@Override
	public void setServerMessages(List<MessageWithSource> newVal) {
		serverMessages = newVal;
		
	}
	@Override
	public List<MessageWithSource> getClientMessages() {
		return clientMessages;
	}
	@Override
	public void setClientMessages(List<MessageWithSource> newVal) {
		clientMessages = newVal;
		
	}
	
	

}
