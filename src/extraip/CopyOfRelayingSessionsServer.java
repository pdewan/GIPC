package extraip;

import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionServer;


public interface CopyOfRelayingSessionsServer extends SessionServer {
	ServerPortDescription getRelayerDescripton(String theSessionName);
//	void relay(String aSessionName, String aRemoteEnd, Object aMessage);
	void setRelayerDescripton(String sessionName,
			ServerPortDescription serverPortDescription);
	void setRelayerDescripton(ServerPortDescription serverPortDescription);
}
