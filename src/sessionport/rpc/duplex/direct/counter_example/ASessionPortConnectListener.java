package sessionport.rpc.duplex.direct.counter_example;

import inputport.ConnectionType;

public class ASessionPortConnectListener implements SessionPortConnectionListener{
	
	public ASessionPortConnectListener() {
		
	}
	
	

	@Override
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		System.out.println ("Connected to:" + aRemoteEndName);
	}

	@Override
	public void notConnected(String aRemoteEndName, String anExplanation,
			ConnectionType aConnectionType) {
		
	}

	@Override
	public void disconnected(String aRemoteEndName,
			boolean anExplicitDsconnection, String anExplanation,
			ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}



	

}
