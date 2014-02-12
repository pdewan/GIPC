package sessionport.datacomm.duplex.object.relayed;

import inputport.ConnectionListener;
import inputport.datacomm.NamingSender;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPort;

import java.util.Set;

import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionObserver;
import sessionport.datacomm.duplex.SessionConnections;
import sessionport.datacomm.duplex.SessionInfo;



public interface RelayingDuplexConnectionsManager  extends 
		SessionObserver, 
		ConnectionListener,
		ReceiveListener<Object>, 
		ByteBufferSendListener,
		NamingSender<Object>,
		Runnable, SessionInfo, SessionConnections
//		ConnectionListener 
		{


	void joinSessionsServer(DuplexRPCClientInputPort aSessionsServerClientPort, 
			ServerPortDescription aSessionClientDescription,
			String aSessionName);
	

	void closeAllConnections();
	DuplexRPCInputPort getSessionsServerInputPort();
	Set<String> getRemoteEndPoints();
	Set<String> getRemoteClients();
	Set<String> getRemoteMembers();
	Set<String> getRemoteServers();
//	void send (String aClientName, Object aMessage);

}
