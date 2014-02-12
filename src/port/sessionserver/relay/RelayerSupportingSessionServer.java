package port.sessionserver.relay;

import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionServer;


public interface RelayerSupportingSessionServer extends SessionServer {
	ServerPortDescription getRelayerDescripton(String theSessionName);
	ServerPortDescription getRelayerDescripton();
//	void relay(String aSessionName, String aRemoteEnd, Object aMessage);
	void setRelayerDescripton(String sessionName,
			ServerPortDescription serverPortDescription);
	void setRelayerDescripton(ServerPortDescription serverPortDescription);
}
