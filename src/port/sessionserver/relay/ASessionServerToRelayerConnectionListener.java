package port.sessionserver.relay;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.InputPort;
import port.ATracingConnectionListener;
import port.sessionserver.ServerPortDescription;

public class ASessionServerToRelayerConnectionListener extends ATracingConnectionListener implements ConnectionListener {
	RelayerSupportingSessionServer sessionServer;
	ServerPortDescription serverPortDescription;
	String sessionName;
	
	public ASessionServerToRelayerConnectionListener(InputPort anInputPort, RelayerSupportingSessionServer aSessionServer, ServerPortDescription aServerPortDescription, String aSessionName ) {
		super(anInputPort);
		sessionServer = aSessionServer;
		serverPortDescription = aServerPortDescription;
		sessionName = aSessionName;
	}

	@Override
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		super.connected(aRemoteEndName, aConnectionType);
		sessionServer.setRelayerDescripton(sessionName, serverPortDescription);
	}

	@Override
	public void notConnected(String aRemoteEndName, String anExplanation, ConnectionType aConnectionType) {
		
	}

	@Override
	public void disconnected(String aRemoteEndName,
			boolean anExplicitDsconnection, String anExplanation, ConnectionType aConnectionType) {
		
	}

}
