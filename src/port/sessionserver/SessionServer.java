package port.sessionserver;

import java.util.List;
import java.util.Set;



public interface SessionServer {
//	JoinInfo join(String theSessionName, 
//			String aMemberName,
//			SessionObserver aSessionObserver);
	JoinInfo join(String theSessionName, 
			ServerPortDescription aPeerDescription,
			SessionObserver aSessionObserver);

	void leave(String sessionName, ServerPortDescription aSessionClientDescription) ;
	
	// server may wish to know about observers, so let us add obsever to them also
	// they can always send a null value
	JoinInfo joinAsServer(String theSessionName, 
			ServerPortDescription aPeerDescription, SessionObserver aSessionObserver);
	
	JoinInfo joinAsClient(String theSessionName, 
			ServerPortDescription aServerPortDescription, SessionObserver aSessionObserver);
	 Set<String> getSessionNames();

	 List<SessionParticipantDescription> getMembers(String theSessionName);
	 List<SessionParticipantDescription> getClients(String theSessionName);
	 List<SessionParticipantDescription> getServers(String theSessionName);



	// should also have operations for getting current members without joining session
	// and for adding as observer
	
	// similarly should have simplex session port in which one sends but does not receive
	// allows implementation of replicated relayed port
	

}
