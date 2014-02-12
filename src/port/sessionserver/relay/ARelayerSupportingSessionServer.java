package port.sessionserver.relay;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.rpc.duplex.DuplexRPCServerInputPort;

import java.util.HashMap;
import java.util.Map;

import port.sessionserver.ASessionServer;
import port.sessionserver.ServerPortDescription;


public class ARelayerSupportingSessionServer extends ASessionServer implements LocalRelayerSupportingSessionsServer, ConnectionListener {
	Map<String, ServerPortDescription> sessionToRelayer = new HashMap();
	ServerPortDescription standardRelayer;
	
	public ARelayerSupportingSessionServer(
			DuplexRPCServerInputPort aDuplexRPCServerInputPort) {
		super(aDuplexRPCServerInputPort);
		aDuplexRPCServerInputPort.addConnectionListener(this);
//		 nextServerId = Integer.parseInt(aDuplexRPCServerInputPort.getServerId()) + 1;
	}
	public ARelayerSupportingSessionServer() {
		
	}
	public void setDuplexRPCServerInputPort(
			DuplexRPCServerInputPort duplexRPCServerInputPort) {
		super.setDuplexRPCServerInputPort(duplexRPCServerInputPort);
		duplexRPCServerInputPort.addConnectionListener(this);
	}
//	static int nextServerId;
//	@Override
//	protected  Session createSession(String aSessionName) {
//		try {
//		ServerPortDescription serverPortDescription = new AServerPortDescription(InetAddress.getLocalHost().getHostName(), ""+nextServerId, aSessionName );
//		nextServerId++;
//		DuplexServerInputPort<Object> port = ObjectDuplexInputPortSelector.createObjectDuplexServerInputPort(serverPortDescription.getID(), 
//				serverPortDescription.getName());
//		port.addConnectListener(this);
//		port.connect();
////		wait();
//		RelayingSession session = new ARelayingSession();
////		nameToSession.put(aSessionName, session);
//		session.setRelayingPort(port);
//		session.setServerPortDescription(serverPortDescription);
//		return session;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	@Override
//	public void relay(String sessionName, String remoteEnd, Object message) {
//		Message.info(this, "Relaying message:" + message + " to " + remoteEnd);
////		Session session =  nameToSession.get(sessionName);
//		DuplexServerInputPort<Object> duplexServerInputPort = get
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
		ServerPortDescription retVal = sessionToRelayer.get(aSessionName);
		if (retVal == null)
			retVal = standardRelayer;
		return retVal;
//		return session.getServerPortDescription();
		
	}
	@Override
	public ServerPortDescription getRelayerDescripton(
			) {
		return standardRelayer;
		
	}
	@Override
	public void setRelayerDescripton(
			String aSessionName, ServerPortDescription aServerPortDescription) {
		if (aSessionName == null)
			setRelayerDescripton(aServerPortDescription);
		else
			sessionToRelayer.put(aSessionName, aServerPortDescription);
		
	}
	@Override
	public void setRelayerDescripton(ServerPortDescription aServerPortDescription) {
		standardRelayer = aServerPortDescription;
		
	}
	@Override
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void notConnected(String aRemoteEndName, String anExplanation,
			ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void disconnected(String aRemoteEndName,
			boolean anExplicitDsconnection, String anExplanation,
			ConnectionType aConnectionType) {
		if (standardRelayer != null && standardRelayer.getName().equals(aRemoteEndName)) {
			standardRelayer = null;
			return;
		}
		String relayerSessionName = null;
		for (String sessionName:sessionToRelayer.keySet()) {
			ServerPortDescription serverPortDescription = sessionToRelayer.get(sessionName);
			if (serverPortDescription.getName().equals(aRemoteEndName)) {
				relayerSessionName =  sessionName;
				break;
			}
		}
		if (relayerSessionName != null) {
			sessionToRelayer.remove(relayerSessionName);
		}

		// TODO Auto-generated method stub
		
	}
	
	
	

}
