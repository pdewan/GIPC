package sessionport.datacomm.group.buffer;

import java.nio.ByteBuffer;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.group.GroupServerInputPort;

public class AGroupSendingConnectListener implements ConnectionListener{
	GroupServerInputPort<ByteBuffer> sessionPort;
	public AGroupSendingConnectListener(GroupServerInputPort<ByteBuffer> aSessionPort) {
		sessionPort = aSessionPort;
	}

	@Override
	public void connected(String remoteEnd, ConnectionType aConnectionType) {
		if (aConnectionType == ConnectionType.MEMBER_TO_SESSION && !sessionPort.getLocalName().equals(remoteEnd)) {
		System.out.print("Connected as client to: " + remoteEnd);
		sessionPort.send(remoteEnd, ByteBuffer.wrap(("Hello from: " + sessionPort.getLocalName() + " to:" + remoteEnd).getBytes()));
		sessionPort.sendAll(ByteBuffer.wrap((remoteEnd  + " connected to:" + sessionPort.getLocalName() + " hey all").getBytes()));
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
