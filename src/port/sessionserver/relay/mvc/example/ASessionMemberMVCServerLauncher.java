package port.sessionserver.relay.mvc.example;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.group.mvc.collaborative.example.AGroupRPCServerCollaborativeMVCLauncher;

import java.net.InetAddress;

import port.sessionserver.AServerPortDescription;
import port.sessionserver.ASessionServer;
import port.sessionserver.SessionServer;
import port.sessionserver.SessionServerLauncher;
import port.sessionserver.example.APrintingSessionObserver;
import port.sessionserverAndRelay.mvc.example.UpperCaseSession;
import sessionport.rpc.group.mvc.flexible.example.ServerSessionPort;



public class ASessionMemberMVCServerLauncher extends  AGroupRPCServerCollaborativeMVCLauncher implements ServerSessionPort {
	SessionServer sessionServerProxy;
	String sessionName, sessionServerName, sessionServerId;
	public ASessionMemberMVCServerLauncher(String aServerName,
			String aServerPort,
			String aSessionServerHost, String aSessionServerId, String aSessionServerName, String aSessionName) {
		super (aServerName, aServerPort);		
		serverHost = aSessionServerHost;
		sessionServerId = aSessionServerId;
		sessionServerName = aSessionServerName;
		sessionName = aSessionName;		
	}
//	public ASessionMemberMVCServerLauncher(String aSessionServerHost, 
//			String aServerId, String aServerName, String aMyId, String aMyName,
//			String aSessionName,
//			SessionChoice aSessionChoice, 
//			boolean aShouldDelay,
//			PortLauncherSupport aDelaysSupport,
//			boolean aDoJitter,
//			boolean aDoCausal, ParticipantChoice aChoice) {
//		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);
//
//	}
	protected  void createProxies() {
		super.createProxies();
		sessionServerProxy = (SessionServer) ((DuplexRPCClientInputPort) auxilliaryPort).getRPCProxyGenerator().generateRPCProxy(ASessionServer.class);				
	}
	protected  InputPort getAuxilliaryPort() {
		return DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
				serverHost, sessionServerId, sessionServerName, 	serverName);	
	}
	// do not want to wait for client to connect to main port
	protected int getNumberOfConnects() {
		return 1;
	}
	protected ConnectionListener getAuxilliaryConnectionListener(InputPort anInputPort) {
		return getConnectionListener(anInputPort);
	}
	protected void doPostConnectsAsyncOperations() {
		String myHostName = "localhost";
		try {
				myHostName = InetAddress.getLocalHost().getHostName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		sessionServerProxy.joinAsServer(sessionName, new AServerPortDescription(myHostName, serverId, serverName), new APrintingSessionObserver());
	
	}
	public static void main (String[] args) {
//		(new ASessionMemberMVCServerLauncher("localhost",
//				SessionServerLauncher.SESSION_SERVER_ID,
//				SessionServerLauncher.SESSION_SERVER_NAME, 
//				 MVC_SERVER_ID, 
//				 MVC_SERVER_NAME, 
//				 UpperCaseSession.SESSION_NAME, 
//				SESSION_CHOICE, 
//				DO_DELAY,
//				DELAYS_SUPPORT, 
//				DO_JITTER,
//                DO_CAUSAL, PARTICIPANT_CHOICE)).launch();
			
		
		(new ASessionMemberMVCServerLauncher(MVC_SERVER_NAME, MVC_SERVER_ID, 
				"localhost", SessionServerLauncher.SESSION_SERVER_ID, SessionServerLauncher.SESSION_SERVER_NAME, UpperCaseSession.SESSION_NAME)).launch();
	}	
}
