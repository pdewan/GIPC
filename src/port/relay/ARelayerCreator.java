package port.relay;

import port.sessionserver.AServerPortDescription;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.relay.ARelayerSupportingSessionsServerCreator;
import port.sessionserver.relay.RelayerSupportingSessionServer;
import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.group.GroupRPCInputPortSelector;
import inputport.rpc.group.GroupRPCServerInputPort;
import util.trace.Tracer;


public class ARelayerCreator {
	public static int RELAYER_PORT = 9200;
	public static String RELAYER_NAME = "Relayer";
	public static void main (String args[]) {
//		Tracer.showInfo(true);
		createAndConnectToSessionsServer("" + RELAYER_PORT, RELAYER_NAME, 
				"localHost", 
				"" + ARelayerSupportingSessionsServerCreator.SESSION_SERVER_PORT, 
				ARelayerSupportingSessionsServerCreator.SESSION_SERVER_NAME);
	}
	
	public static GroupRPCServerInputPort createAndConnectToSessionsServer (String relayerId, String relayerName, String sessionsServerHost, String sessionsServerId, String sessionsServerName) {
		GroupRPCServerInputPort retVal = createRelayer(relayerId, relayerName);
		registerWithSessionsServer(relayerId, relayerName, sessionsServerHost, sessionsServerId, sessionsServerName);
		return retVal;
	}
	public static GroupRPCServerInputPort createRelayer (String relayerId, String relayerName) {
		GroupRPCServerInputPort serverInputPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort(relayerId, relayerName);
		Relayer relayer = new ARelayer(serverInputPort);
		serverInputPort.register(Relayer.class, relayer);
		serverInputPort.register(relayerName, relayer);
		serverInputPort.connect();
		return serverInputPort;
				
	}
	public static void registerWithSessionsServer (String relayerId, String relayerName, String sessionsServerHost, String sessionsServerId, String sessionsServerName) {		
		ServerPortDescription serverPortDescription = new AServerPortDescription("localhost", relayerId, relayerName);
		DuplexRPCClientInputPort sessionServerClientPort = DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
				sessionsServerHost, sessionsServerId, 
				sessionsServerName, relayerName);
		RelayerSupportingSessionServer sessionsServer =  (RelayerSupportingSessionServer) DirectedRPCProxyGenerator.generateRPCProxy(sessionServerClientPort, null, RelayerSupportingSessionServer.class, sessionsServerName);
		sessionServerClientPort.connect();
		sessionsServer.setRelayerDescripton(serverPortDescription);		
	}
	

}
