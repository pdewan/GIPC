package port.sessionserver.relay.late;

import java.util.List;

import port.sessionserver.Session;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public interface LatecomerSession extends Session {
	List<MessageWithSource> getMessages();
	void addMessage(MessageWithSource aMessage);
	void clearMessages();
	
	List<MessageWithSource> getServerMessages() ;	
	void addServerMessage(MessageWithSource message);
	void clearServerMessages() ;
	
	List<MessageWithSource> getClientMessages() ;	
	void addClientMessage(MessageWithSource message);
	void clearClientMessages() ;
}
