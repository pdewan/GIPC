package port.sessionserver.relay;

import inputport.datacomm.duplex.DuplexServerInputPort;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.Session;

public interface RelayingSession extends Session {
	void setRelayingPort (DuplexServerInputPort<Object> aDuplexServeInputPort);
	DuplexServerInputPort<Object> getRelayingPort ();
	void relay(String aRemoteEnd, Object aMessage);
	ServerPortDescription getServerPortDescription() ;
	void setServerPortDescription(ServerPortDescription serverPortDescription) ;
}
