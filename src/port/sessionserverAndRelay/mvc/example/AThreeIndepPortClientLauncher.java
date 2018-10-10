package port.sessionserverAndRelay.mvc.example;


import java.util.List;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.simplex.mvc.example.SimplexRPCServerMVCLauncher;
import port.relay.RelayerLauncher;
import port.relay.mvc.example.ATwoServerClientLauncher;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.ASessionServer;
import port.sessionserver.JoinInfo;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.SessionServer;
import port.sessionserver.SessionServerLauncher;
import port.sessionserver.example.APrintingSessionObserver;

public class AThreeIndepPortClientLauncher extends ATwoServerClientLauncher  {
	String sessionServerHost, sessionServerId, sessionServerName, sessionName;
	SessionServer sessionServerProxy;
	public AThreeIndepPortClientLauncher(String aClientName, 
			String aServerHost, String aServerId, String aServerName, 
			String aRelayerHost, String aRelayerId, String aRelayerName,
			String aSessionServerHost, String aSessionServerId, String aSessionServerName, String aSessionName) {
		super(aClientName, aServerHost, aServerId, aServerName, aRelayerHost, aRelayerId, aRelayerName);
		sessionServerHost =aSessionServerHost;
		sessionServerId = aSessionServerId;
		sessionServerName = aSessionServerName;
		sessionName = aSessionName;
	}
	protected  void createProxies() {
		super.createProxies();
		sessionServerProxy = (SessionServer) ((DuplexRPCClientInputPort) tertiaryPort).getRPCProxyGenerator().generateRPCProxy(ASessionServer.class);
	}
	protected  InputPort getTertiaryPort() {
		return DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
				sessionServerHost, sessionServerId, sessionServerName, 	clientName);	
	}	
	protected  ConnectionListener getTertiaryConnectionListener (InputPort anInputPort) {
		return getConnectionListener(anInputPort);
	}		
	protected  void createUI (InputPort anInputPort) {
		JoinInfo joinInfo = sessionServerProxy.join(sessionName, new AServerPortDescription(null, null, clientName), new APrintingSessionObserver());
		processInitialSessionMembers(joinInfo);
		super.createUI(anInputPort);		
	}
	
	void processInitialSessionMembers (JoinInfo joinInfo) {
		List<SessionParticipantDescription> members =  joinInfo.getMembers();
		for (ServerPortDescription member:members) {
			System.out.println("Session member: " + member);
		}
	}	
	public static void main (String[] args) {		
		(new AThreeIndepPortClientLauncher(
				CLIENT_NAME, 
				"localhost", SimplexRPCServerMVCLauncher.MVC_SERVER_ID, SimplexRPCServerMVCLauncher.MVC_SERVER_NAME,
				"localhost", RelayerLauncher.RELAYER_ID, RelayerLauncher.RELAYER_NAME,
				"localhost", SessionServerLauncher.SESSION_SERVER_ID, SessionServerLauncher.SESSION_SERVER_NAME, UpperCaseSession.SESSION_NAME
				)).launch();
	}
}
