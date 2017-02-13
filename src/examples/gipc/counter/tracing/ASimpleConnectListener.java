package examples.gipc.counter.tracing;

import inputport.ConnectionListener;
import inputport.ConnectionType;

public class ASimpleConnectListener implements ConnectionListener{

	@Override
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		System.out.println("connected to: " + 	aRemoteEndName);	

		
	}

	@Override
	public void notConnected(String aRemoteEndName, String anExplanation,
			ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnected(String aRemoteEndName,
			boolean anExplicitDsconnection, String anExplanation,
			ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}

}
