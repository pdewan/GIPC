package port.sessionserver.relay;

import inputport.rpc.duplex.DuplexRPCServerInputPort;
import port.sessionserver.ASessionsServerCreator;
import port.sessionserver.LocalSessionsServer;



public class ARelayerSupportingSessionsServerCreator extends ASessionsServerCreator{
	public static int SESSION_SERVER_PORT = 9090;
	public static String SESSION_SERVER_NAME = "Session Server";

	public static void main (String args[]) {
//		Tracer.showInfo(true);
		createRelayerSupportingSessionsServer("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME);
	}
	
	public static DuplexRPCServerInputPort createRelayerSupportingSessionsServer (String aServerId, String aServerName) {
		return createRelayerSupportingSessionsServer(aServerId, aServerName, aServerName);
	}	
	public static DuplexRPCServerInputPort createRelayerSupportingSessionsServer (String aServerId, String aServerName, String aLogicalServerName) {

		LocalSessionsServer aSessionsServer = new ARelayerSupportingSessionServer();
		return createSessionsServer(aServerId, aServerName, aSessionsServer, RelayerSupportingSessionServer.class, aLogicalServerName);
	}	

}
