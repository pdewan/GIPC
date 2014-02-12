package port.sessionserver.relay;

import port.sessionserver.ASession;
import port.sessionserver.ServerPortDescription;
import inputport.datacomm.duplex.DuplexServerInputPort;


public class ARelayingSession extends ASession implements RelayingSession{
	DuplexServerInputPort<Object> duplexServerInputPort;
	ServerPortDescription serverPortDescription;
	public ServerPortDescription getServerPortDescription() {
		return serverPortDescription;
	}
	public void setServerPortDescription(ServerPortDescription aServerPortDescription) {
		serverPortDescription = aServerPortDescription;
	}
	@Override
	public DuplexServerInputPort<Object> getRelayingPort() {
		return duplexServerInputPort;
	}
	@Override
	public void setRelayingPort(
			DuplexServerInputPort<Object> aDuplexServeInputPort) {
		duplexServerInputPort = aDuplexServeInputPort;
	}
	@Override
	public void relay(String remoteEnd, Object message) {
		duplexServerInputPort.send(remoteEnd, message);
	}
	
}
