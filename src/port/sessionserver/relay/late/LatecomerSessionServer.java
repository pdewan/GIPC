package port.sessionserver.relay.late;

import java.util.List;

import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionObserver;
import port.sessionserver.relay.RelayerSupportingSessionServer;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public interface LatecomerSessionServer extends RelayerSupportingSessionServer {
	LatecomerJoinInfo lateJoin(String theSessionName, 
			ServerPortDescription aPeerDescription,
			SessionObserver aSessionObserver);
	LatecomerJoinInfo lateJoinAsServer(String aSessionName,
			ServerPortDescription aSessionClientDescription, SessionObserver aSessionObserver);
	LatecomerJoinInfo lateJoinAsClient(String aSessionName,
			ServerPortDescription aSessionClientDescription, SessionObserver aSessionObserver);
	List<MessageWithSource> getMessages(String aSessionName);
	void addMessage(String aSessionName, MessageWithSource aMessage);
	void clearMessages(String aSessionName);
	
	List<MessageWithSource> getServerMessages(String aSessionName) ;	
	void addServerMessage(String aSessionName, MessageWithSource message);
	void clearServerMessages(String aSessionName) ;
	
	List<MessageWithSource> getClientMessages(String aSessionName) ;	
	void addClientMessage(String aSessionName, MessageWithSource message);
	void clearClientMessages(String aSessionName) ;

//	LatecomerJoinInfo join(String theSessionName, 
//			ServerPortDescription aPeerDescription,
//			SessionObserver aSessionObserver);	


}
