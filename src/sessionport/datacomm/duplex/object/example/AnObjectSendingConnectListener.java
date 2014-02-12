package sessionport.datacomm.duplex.object.example;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.duplex.DuplexServerInputPort;

public class AnObjectSendingConnectListener implements ConnectionListener{
	DuplexServerInputPort<Object> sessionPort;
	public AnObjectSendingConnectListener(DuplexServerInputPort<Object> aSessionPort) {
		sessionPort = aSessionPort;
	}

	@Override
	public void connected(String remoteEnd, ConnectionType aConnectionType) {
		System.out.println("Connected as client to: " + remoteEnd);
//		sessionPort.send(remoteEnd, ByteBuffer.wrap(("Hello from: " + sessionPort.getLocalName() + " to:" + remoteEnd).getBytes()));
		sessionPort.send(remoteEnd, "Hello from: " + sessionPort.getLocalName() + " to:" + remoteEnd);		
	}

	@Override
	public void disconnected(String remoteEndName,
			boolean explicitDsconnection, String systemMessage, ConnectionType aConnectionType) {
		
	}

	@Override
	public void notConnected(String remoteEnd, String message, ConnectionType aConnectionType) {
		
	}

}
