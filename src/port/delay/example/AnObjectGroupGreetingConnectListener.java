package port.delay.example;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.group.GroupServerInputPort;

public class AnObjectGroupGreetingConnectListener implements ConnectionListener{
	GroupServerInputPort<Object> sessionPort;
	public AnObjectGroupGreetingConnectListener(GroupServerInputPort<Object> aSessionPort) {
		sessionPort = aSessionPort;
	}

	@Override
	public void connected(String remoteEnd, ConnectionType aConnectionType) {
//		if (!remoteEnd.equals(sessionPort.getLocalName())) {
//			String message = "Broadcasting that" + remoteEnd + " connected to " + sessionPort.getLocalName();
//			System.out.println(message);
//			sessionPort.sendAll(message );
//			
//		}
		if (remoteEnd.equals(sessionPort.getLocalName())) {
			String message = "Greetings to all from: " + remoteEnd;
			sessionPort.sendAll("Greetings to all from: " + remoteEnd );

		}
//		System.out.println("Connected as client to: " + remoteEnd);
//		sessionPort.send(remoteEnd, "Hello from: " + sessionPort.getLocalName() + " to:" + remoteEnd);	
//		sessionPort.sendAll("broadcasting that: " + remoteEnd  + " connected to:" + sessionPort.getLocalName());

	}

	@Override
	public void disconnected(String remoteEndName,
			boolean explicitDsconnection, String systemMessage, ConnectionType aConnectionType) {
		
	}

	@Override
	public void notConnected(String remoteEnd, String message, ConnectionType aConnectionType) {
		
	}

}
