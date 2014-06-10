package sessionport.datacomm.group.object.example;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.group.GroupServerInputPort;

public class AnObjectGroupSendingConnectListener implements ConnectionListener{
	GroupServerInputPort<Object> sessionPort;
	public AnObjectGroupSendingConnectListener(GroupServerInputPort<Object> aSessionPort) {
		sessionPort = aSessionPort;
	}

	@Override
	public void connected(String remoteEnd, ConnectionType aConnectionType) {
//		System.out.println("Connected as client to " + remoteEnd);
////		sessionPort.send(remoteEnd, "Hello from: " + sessionPort.getLocalName() + " to:" + remoteEnd);	
//		sessionPort.sendAll("Broadcasting that " + remoteEnd  + " connected to " + sessionPort.getLocalName());
//		
		if (!remoteEnd.equals(sessionPort.getLocalName()) && aConnectionType == ConnectionType.MEMBER_TO_SESSION) {
			String message = sessionPort.getLocalName() + " connected to " + remoteEnd;
//			System.out.println("Broadcasting message: " + message);
			System.out.println(message);
			sessionPort.sendAll(message );
			sessionPort.send(remoteEnd, "Words of Robert Frost Please");
//			System.out.println("Finished Broadcasting message: " + message);

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
