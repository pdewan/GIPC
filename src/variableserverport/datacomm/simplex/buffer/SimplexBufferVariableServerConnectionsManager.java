package variableserverport.datacomm.simplex.buffer;

import java.nio.ByteBuffer;
import java.util.Set;

import inputport.ConnectionListener;
import inputport.datacomm.NamingSender;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;
import port.sessionserver.SessionObserver;
import sessionport.datacomm.duplex.SessionConnections;
import sessionport.datacomm.duplex.SessionInfo;



public interface SimplexBufferVariableServerConnectionsManager  extends 
		SessionObserver, 
//		ReceiveListener<ByteBuffer>, 
		ByteBufferSendListener, ConnectionListener, NamingSender<ByteBuffer>, SessionInfo, SessionConnections {

	void closeAllConnections();
//	public void connect(ServerPortDescription[] otherServers);
//	DuplexRPCInputPort getSessionsServerInputPort();
	Set<String> getRemoteEndPoints();
//	void send(String remoteName, Object message);


}
