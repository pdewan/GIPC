package port.sessionserver.relay.late;

import java.io.Serializable;
import java.util.List;

import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public interface LatecomerMessages  extends Serializable{
	List<MessageWithSource> getMessages() ;
	void setMessages(List<MessageWithSource> messages) ;
	List<MessageWithSource> getServerMessages() ;
	void setServerMessages(List<MessageWithSource> messages) ;
	List<MessageWithSource> getClientMessages() ;
	void setClientMessages(List<MessageWithSource> messages) ;

}
