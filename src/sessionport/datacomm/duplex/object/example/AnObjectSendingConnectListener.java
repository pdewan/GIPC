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
		if (aConnectionType == ConnectionType.MEMBER_TO_SESSION || aConnectionType == ConnectionType.SERVER_TO_SESSION) {
			String message = "Hello from: " + sessionPort.getLocalName() + " to:" + remoteEnd;
			System.out.println("Sending message:" + message + " to:" + remoteEnd);
			sessionPort.send(remoteEnd, message);	
			
		}
	}

	@Override
	public void disconnected(String remoteEndName,
			boolean explicitDsconnection, String systemMessage, ConnectionType aConnectionType) {
		
	}

	@Override
	public void notConnected(String remoteEnd, String message, ConnectionType aConnectionType) {
		
	}

}
