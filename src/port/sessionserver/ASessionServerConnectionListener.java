package port.sessionserver;

import inputport.ConnectionListener;
import inputport.ConnectionType;

public class ASessionServerConnectionListener implements ConnectionListener{
	SessionServer sessionServer;
	public ASessionServerConnectionListener(SessionServer aSessionServer) {
		sessionServer = aSessionServer;
	}
	@Override
	public void connected(String remoteEndName, ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notConnected(String remoteEndName, String anExplanation, ConnectionType aConnectionType) {
		
	}

	@Override
	public void disconnected(String remoteEndName,
			boolean anExplicitDsconnection, String anExplanation, ConnectionType aConnectionType) {
	}

}
