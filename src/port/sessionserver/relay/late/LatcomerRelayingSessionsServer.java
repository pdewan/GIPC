package port.sessionserver.relay.late;

import java.util.List;

import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionObserver;
import port.sessionserver.SessionServer;


public interface LatcomerRelayingSessionsServer extends SessionServer {
	List<ServerPortDescription> lateJoin(String theSessionName, 
			ServerPortDescription aPeerDescription,
			SessionObserver aSessionObserver);

}
