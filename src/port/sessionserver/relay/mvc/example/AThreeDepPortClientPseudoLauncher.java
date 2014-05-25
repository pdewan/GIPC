package port.sessionserver.relay.mvc.example;


import inputport.ConnectionListenerWithPort;
import inputport.ConnectionType;
import inputport.InputPort;
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.simplex.mvc.example.SimplexRPCClientMVCLauncher;

import java.util.List;

import port.AnAbstractPortLauncher;
import port.PortLauncherSupport;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.JoinInfo;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.SessionServerLauncher;
import port.sessionserver.asymmetricexample.APrintingSessionObserver;
import port.sessionserver.relay.ARelayerSupportingSessionServer;
import port.sessionserver.relay.RelayerSupportingSessionServer;
import port.sessionserverAndRelay.mvc.example.UpperCaseSession;
import util.trace.Tracer;

public class AThreeDepPortClientPseudoLauncher extends AnAbstractPortLauncher  {
	protected RelayerSupportingSessionServer sessionServerProxy;
	protected String sessionName;
	public AThreeDepPortClientPseudoLauncher(String aClientName, 
			String aSessionServerHost, String aSessionServerId, String aSessionServerName, String aSessionName) {
		super (aClientName, aSessionServerHost, aSessionServerId, aSessionServerName);		
		sessionName = aSessionName;			
	}
	@Override
	protected  void createProxies() {
		sessionServerProxy = (RelayerSupportingSessionServer) ((DuplexRPCClientInputPort) mainPort).getRPCProxyGenerator().generateRPCProxy(sessionServerClass());				
	}
	protected Class sessionServerClass() {
		return ARelayerSupportingSessionServer.class;
	}
	@Override
	protected  InputPort getPort() {
		return DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
				serverHost, serverId, serverName, 	clientName);	
	}
	@Override
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ADuplexRPCInputPortLauncherSupport();
	}
	@Override
	protected void doPostConnectsAsyncOperations() {
		ServerPortDescription relayerPortDescription =  sessionServerProxy.getRelayerDescripton(sessionName);		
		JoinInfo joinInfo = joinSessionServer();
		ServerPortDescription mvcServerPortDescription = null; 
		List<SessionParticipantDescription> servers = joinInfo.getServers();
		if (servers.size() > 0) {
			mvcServerPortDescription = servers.get(0);
		} else {
			mvcServerHasNotJoined();
		}
		processMemberJoinInfo(joinInfo);
		chainConnectListeners(relayerPortDescription, mvcServerPortDescription);
		
	}
	protected void mvcServerHasNotJoined() {
		Tracer.error("MVC Server not yet registered");		
	}

	protected JoinInfo joinSessionServer() {
		return sessionServerProxy.join(sessionName, new AServerPortDescription(null, null, clientName), new APrintingSessionObserver());
	}
	protected void chainConnectListeners(ServerPortDescription relayerPortDescription, ServerPortDescription mvcServerPortDescription) {
		ConnectionListenerWithPort relayerConnectionListener = new AnOldRelayerConnectionListener(mvcServerPortDescription, clientName, null);
		ConnectionListenerWithPort dummyConnectionListener = new AServerConnectingConnectionListener(relayerPortDescription, clientName, relayerConnectionListener);
		dummyConnectionListener.initInputPort(mainPort);
		dummyConnectionListener.connected(serverName, ConnectionType.CLIENT_TO_SESSION);		
	}
	protected void processMemberJoinInfo (JoinInfo joinInfo) {
		processInitialSessionMembers (joinInfo);
	}
	protected void processInitialSessionMembers (JoinInfo joinInfo) {
		List<SessionParticipantDescription> members =  joinInfo.getMembers();
		for (ServerPortDescription member:members) {
			System.out.println("Session member: " + member);
		}
	}	
	public static void main (String[] args) {		
		(new AThreeDepPortClientPseudoLauncher(
				SimplexRPCClientMVCLauncher.CLIENT_NAME, 
				"localhost", SessionServerLauncher.SESSION_SERVER_ID, SessionServerLauncher.SESSION_SERVER_NAME, UpperCaseSession.SESSION_NAME
				)).launch();
	}
}
