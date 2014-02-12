package extraip;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.rpc.duplex.DuplexRPCServerInputPort;

import java.net.InetAddress;

import port.sessionserver.AServerPortDescription;
import port.sessionserver.ASessionServer;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.Session;
import port.sessionserver.relay.ARelayingSession;
import port.sessionserver.relay.RelayerSupportingSessionServer;
import port.sessionserver.relay.RelayingSession;


public class CopyOfARelayingSessionsServer extends ASessionServer implements RelayerSupportingSessionServer, ConnectionListener {
	public CopyOfARelayingSessionsServer(
			DuplexRPCServerInputPort aDuplexRPCServerInputPort) {
		super(aDuplexRPCServerInputPort);
		 nextServerId = Integer.parseInt(aDuplexRPCServerInputPort.getServerId()) + 1;
	}
	static int nextServerId;
	@Override
	protected  Session createSession(String aSessionName) {
		try {
		ServerPortDescription serverPortDescription = new AServerPortDescription(InetAddress.getLocalHost().getHostName(), ""+nextServerId, aSessionName );
		nextServerId++;
		DuplexServerInputPort<Object> port = DuplexObjectInputPortSelector.createDuplexServerInputPort(serverPortDescription.getID(), 
				serverPortDescription.getName());
		port.addConnectionListener(this);
		port.connect();
//		wait();
		RelayingSession session = new ARelayingSession();
//		nameToSession.put(aSessionName, session);
		session.setRelayingPort(port);
		session.setServerPortDescription(serverPortDescription);
		return session;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
//	@Override
//	public void relay(String sessionName, String remoteEnd, Object message) {
//		Message.info(this, "Relaying message:" + message + " to " + remoteEnd);
//		RelayingSession session = (RelayingSession) nameToSession.get(sessionName);
//		DuplexServerInputPort<Object> duplexServerInputPort = session.getRelayingPort();
//		duplexServerInputPort.send(remoteEnd, message);		
//	}	
//	@Override
//	public List<ServerPortDescription> join(String aSessionName,
//			ServerPortDescription aSessionClientDescription, SessionObserver aSessionObserver) {
//		List<ServerPortDescription> retVal = super.join(aSessionName, aSessionClientDescription, aSessionObserver);
//		RelayingSession session = (RelayingSession) nameToSession.get(aSessionName);
//		retVal.add(session.getServerPortDescription());
//		return retVal;
//	}	
//	public static void main (String args[]) {
//		Message.showInfo(true);
////		GroupRPCServerInputPort serverInputPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort("" + 
////				SESSION_SERVER_PORT, SESSION_SERVER_NAME);
//		DuplexRPCServerInputPort serverInputPort = DuplexRPCInputPortSelector.createDuplexRPCServerInputPort("" + 
//				SESSION_SERVER_PORT, SESSION_SERVER_NAME);
//		SessionsServer sessionServer = new ARelayingSessionsServer(serverInputPort);
//		serverInputPort.register(RelayingSessionsServer.class, sessionServer);
//		serverInputPort.register(SESSION_SERVER_NAME, sessionServer);
//
//		serverInputPort.connect();		
//	}
	@Override
	public ServerPortDescription getRelayerDescripton(
			String aSessionName) {
		RelayingSession session = (RelayingSession) getAndMaybeCreateSession(aSessionName);
		return session.getServerPortDescription();
		
	}
	
	@Override
	public synchronized void connected(String remoteEnd, ConnectionType aConnectionType) {
		notify();
	}
	@Override
	public void disconnected(String remoteEndName,
			boolean explicitDsconnection, String systemMessage, ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void notConnected(String remoteEnd, String message, ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setRelayerDescripton(String sessionName,
			ServerPortDescription serverPortDescription) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setRelayerDescripton(ServerPortDescription serverPortDescription) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ServerPortDescription getRelayerDescripton() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
