package port.sessionserver.relay.mvc.example;


import inputport.ConnectionListenerWithPort;
import inputport.ConnectionType;
import inputport.InputPort;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.mvc.singleuser.example.ADuplexRPCClientMVCLauncher;
import inputport.rpc.simplex.mvc.example.SimplexRPCClientMVCLauncher;

import java.util.List;

import port.APortDescription;
import port.PortAccessKind;
import port.PortDescription;
import port.PortKind;
import port.PortLauncherSupport;
import port.PortMessageKind;
import port.relay.mvc.example.AGenericRelayingCollaborativeFrostyModel;
import port.relay.mvc.example.GenericRelayingCollaborativeFrostyModel;
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
import examples.mvc.local.duplex.DuplexFrostyModel;

public class AThreeDepPortClientLauncher extends   ADuplexRPCClientMVCLauncher   {
	protected RelayerSupportingSessionServer sessionServerProxy;
	protected String sessionName;
	GenericRelayingCollaborativeFrostyModel genericModel;
	public AThreeDepPortClientLauncher(String aClientName, 
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
//	@Override
//	protected  InputPort getPort() {
//		return DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
//				serverHost, serverId, serverName, 	clientName);	
//	}
	@Override
	protected  InputPort getPort() {
		return getDefaultPort();
	}

	@Override
	protected PortLauncherSupport getPortLauncherSupport() {
		return getDefaultPortLauncherSupport();
	}
	@Override
	protected PortDescription getPortDescription() {
		return new APortDescription(PortKind.CLIENT_INPUT_PORT, PortAccessKind.DUPLEX, PortMessageKind.RPC );
	}
	
	@Override
	protected DuplexFrostyModel getFrostyModel() {
		if (genericModel == null)
		genericModel =  new  AGenericRelayingCollaborativeFrostyModel(null, null, counter, mainPort.getLocalName());	
//		((DuplexRPCInputPort) relayerPort).addReceiveListener(genericModel);
		return genericModel;
	}
//	protected void createUI(InputPort anInputPort) {
//		communicateWithSessionServer();
//		// this is the infinite loop
//		super.createUI(anInputPort);
//	}
	protected void processConsoleInput() {
		communicateWithSessionServer();
		// this is the infinite loop
		super.processConsoleInput();
	}
	
	protected void communicateWithSessionServer () {
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
		ConnectionListenerWithPort relayerConnectionListener = new ARelayerConnectionListener(mvcServerPortDescription, clientName, null,
				( GenericRelayingCollaborativeFrostyModel) getFrostyModel());
		ConnectionListenerWithPort startConnectionListener = new AServerConnectingConnectionListener(relayerPortDescription, clientName, relayerConnectionListener);
		startConnectionListener.initInputPort(mainPort);
		startConnectionListener.connected(serverName, ConnectionType.CLIENT_TO_SESSION);		
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
		(new AThreeDepPortClientLauncher(
				SimplexRPCClientMVCLauncher.CLIENT_NAME, 
				"localhost", SessionServerLauncher.SESSION_SERVER_ID, SessionServerLauncher.SESSION_SERVER_NAME, UpperCaseSession.SESSION_NAME
				)).launch();
	}
}
