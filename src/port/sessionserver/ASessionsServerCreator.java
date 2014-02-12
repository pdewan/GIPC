package port.sessionserver;

import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import util.trace.Tracer;



public class ASessionsServerCreator {
	public static int SESSION_SERVER_PORT = 9090;
	public static String SESSION_SERVER_NAME = "Sessions Server";
	public static void main (String args[]) {
//		Tracer.showInfo(true);
		createSessionsServer("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME);
//		GroupRPCServerInputPort serverInputPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort("" + 
//				SESSION_SERVER_PORT, SESSION_SERVER_NAME);
//		SessionsServer sessionServer = new ASessionsServer(serverInputPort);
//		serverInputPort.register(SessionsServer.class, sessionServer);
//		serverInputPort.register(SESSION_SERVER_NAME, sessionServer);
//		serverInputPort.connect();		
	}
	public static DuplexRPCServerInputPort createSessionsServer (String aServerId, String aServerName) {
		return createSessionsServer(aServerId, aServerName, aServerName);	
	}
	public static DuplexRPCServerInputPort createSessionsServer (String aServerId, String aServerName, String aLogicalServerName) {
		LocalSessionsServer aSessionsServer = new ASessionServer();
		return createSessionsServer(aServerId, aServerName, aSessionsServer, SessionServer.class, aLogicalServerName);
	}

	public static DuplexRPCServerInputPort createSessionsServer(String aServerId, 
			String aServerName, 
			LocalSessionsServer aSessionsServer,
			Class aSessionsServerInterface, String aRegisteredServerName) {		
		DuplexRPCServerInputPort serverInputPort = DuplexRPCInputPortSelector.createDuplexRPCServerInputPort(aServerId, aServerName);
//		SessionsServer sessionServer = new ASessionsServer(serverInputPort);
		aSessionsServer.setDuplexRPCServerInputPort(serverInputPort);
		serverInputPort.register(aSessionsServerInterface, aSessionsServer);
		serverInputPort.register(aRegisteredServerName, aSessionsServer);
		serverInputPort.connect();	
		return serverInputPort;
	}
	public static DuplexRPCServerInputPort createSessionsServer(String aServerId, 
			String aServerName, 
			LocalSessionsServer aSessionsServer,
			Class aSessionsServerInterface) {		
		return createSessionsServer(aServerId, aServerName, aSessionsServer, aSessionsServerInterface, aServerName);
	}
	
}
