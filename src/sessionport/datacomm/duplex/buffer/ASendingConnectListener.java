package sessionport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.duplex.DuplexServerInputPort;

public class ASendingConnectListener implements ConnectionListener{
	DuplexServerInputPort<ByteBuffer> sessionPort;
	public ASendingConnectListener(DuplexServerInputPort<ByteBuffer> aSessionPort) {
		sessionPort = aSessionPort;
	}

	@Override
	public void connected(String remoteEnd, ConnectionType aConnectionType) {
		System.out.print("Connected as client to: " + remoteEnd);
		sessionPort.send(remoteEnd, ByteBuffer.wrap(("Hello from: " + sessionPort.getLocalName() + " to:" + remoteEnd).getBytes()));
		
	}

	@Override
	public void disconnected(String remoteEndName,
			boolean explicitDsconnection, String systemMessage, ConnectionType aConnectionType) {
		
	}

	@Override
	public void notConnected(String remoteEnd, String message, ConnectionType aConnectionType) {
		
	}

}
