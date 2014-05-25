package port.delay.example;

import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.group.GroupRPCInputPortSelector;
import inputport.rpc.group.GroupRPCServerInputPort;
import port.relay.ARelayer;
import port.relay.Relayer;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.relay.ARelayerSupportingSessionServer;
import port.sessionserver.relay.RelayerSupportingSessionServer;


public class ADelayingRelayingSessionsServerLauncher {
	public static int SESSION_SERVER_PORT = 9090;
	public static String SESSION_SERVER_NAME = "Session Server";
	public static int RELAYER_PORT = 9091;
	public static String RELAYER_NAME = "Relayer";
	public static void main (String args[]) {
//		Tracer.showInfo(true);
//		Message.setKeyWordStatus(Message.ALL_KEYWORDS, false);
//		Message.setKeyWordStatus("nioip", true);

//		GlobalState.setDelayServerBufferSends(true);
//		GlobalState.getDelayManager().setMinimumDelay("Alice", 1000);
//		GlobalState.getDelayManager().setMinimumDelay("Bob", 100);
//		GlobalState.getDelayManager().setMinimumDelay("Cathy", 500);
//		GroupRPCServerInputPort serverInputPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort("" + 
//				ASessionsServer.SESSION_SERVER_PORT, ASessionsServer.SESSION_SERVER_NAME);
		DuplexRPCServerInputPort serverInputPort = DuplexRPCInputPortSelector.createDuplexRPCServerInputPort("" + 
				SESSION_SERVER_PORT, SESSION_SERVER_NAME);
		RelayerSupportingSessionServer sessionServer = new ARelayerSupportingSessionServer(serverInputPort);
		ServerPortDescription serverPortDescription = new AServerPortDescription("localhost", "" + RELAYER_PORT, RELAYER_NAME);
		sessionServer.setRelayerDescripton(serverPortDescription);		
		serverInputPort.register(RelayerSupportingSessionServer.class, sessionServer);
		serverInputPort.register(SESSION_SERVER_NAME, sessionServer);
		serverInputPort.connect();	
		
		GroupRPCServerInputPort relayerPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort("" + 
				RELAYER_PORT, RELAYER_NAME);
		Relayer relayer = new ARelayer(relayerPort);
		relayerPort.register(Relayer.class, relayer);
		serverInputPort.register(RELAYER_NAME, relayer);


		relayerPort.connect();		

	}
	

}
