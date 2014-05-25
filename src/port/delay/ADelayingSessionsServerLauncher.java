package port.delay;


import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import port.sessionserver.relay.ARelayerSupportingSessionServer;
import port.sessionserver.relay.RelayerSupportingSessionServer;
import util.trace.Tracer;


public class ADelayingSessionsServerLauncher {
	public static int SESSION_SERVER_PORT = 9090;
	public static String SESSION_SERVER_NAME = "Session Server";
//	public static int RELAYER_PORT = 9091;
//	public static String RELAYER_NAME = "Relayer";
	public static void main (String args[]) {
		Tracer.showInfo(true);
//		Message.setKeyWordStatus(Message.ALL_KEYWORDS, false);
//		Message.setKeyWordStatus("nioip", true);

		DelayUtlity.setDelayServerBufferSends();
		DelayUtlity.getDelayManager().setMinimumDelay("Alice", 1000);
		DelayUtlity.getDelayManager().setMinimumDelay("Bob", 100);
		DelayUtlity.getDelayManager().setMinimumDelay("Cathy", 500);
//		GroupRPCServerInputPort serverInputPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort("" + 
//				ASessionsServer.SESSION_SERVER_PORT, ASessionsServer.SESSION_SERVER_NAME);
		DuplexRPCServerInputPort serverInputPort = DuplexRPCInputPortSelector.createDuplexRPCServerInputPort("" + 
				SESSION_SERVER_PORT, SESSION_SERVER_NAME);
		RelayerSupportingSessionServer sessionServer = new ARelayerSupportingSessionServer(serverInputPort);
//		ServerPortDescription serverPortDescription = new AServerPortDescription("localhost", "" + RELAYER_PORT, RELAYER_NAME);
//		sessionServer.setRelayerDescripton(serverPortDescription);		
		serverInputPort.register(RelayerSupportingSessionServer.class, sessionServer);
		serverInputPort.register(SESSION_SERVER_NAME, sessionServer);
		serverInputPort.connect();	
		
//		DuplexRPCServerInputPort relayerPort = DuplexRPCInputPortSelector.createDuplexRPCServerInputPort("" + 
//				RELAYER_PORT, RELAYER_NAME);
//		Relayer relayer = new ARelayer(relayerPort);
//		relayerPort.register(Relayer.class, relayer);
//		serverInputPort.register(RELAYER_NAME, relayer);
//
//
//		relayerPort.connect();		

	}
	

}
