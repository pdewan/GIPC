package port.delay.example;


import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.group.GroupRPCInputPortSelector;
import inputport.rpc.group.GroupRPCServerInputPort;
import port.delay.ADelayingSessionsServerLauncher;
import port.delay.DelayUtlity;
import port.relay.ARelayer;
import port.relay.Relayer;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.relay.RelayerSupportingSessionServer;
import util.trace.Tracer;


public class ADelayingRelayerLauncher {
	public static int RELAYER_PORT = 9091;
	public static String RELAYER_NAME = "Relayer";
	public static void main (String args[]) {
//		Tracer.showInfo(true);
//		Message.setKeyWordStatus(Message.ALL_KEYWORDS, false);
//		Message.setKeyWordStatus("nioip", true);

//		GlobalState.setDelayServerBufferSends(true);
		DelayUtlity.getDelayManager().setMinimumDelay("Alice", 1000);
		DelayUtlity.getDelayManager().setMinimumDelay("Bob", 100);
		DelayUtlity.getDelayManager().setMinimumDelay("Cathy", 500);
//		GroupRPCServerInputPort serverInputPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort("" + 
//				ASessionsServer.SESSION_SERVER_PORT, ASessionsServer.SESSION_SERVER_NAME);
	
		
		GroupRPCServerInputPort serverInputPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort("" + 
				RELAYER_PORT,RELAYER_NAME);
		
		Relayer relayer = new ARelayer(serverInputPort);
		serverInputPort.register(Relayer.class, relayer);
		serverInputPort.register(RELAYER_NAME, relayer);
		serverInputPort.connect();	
		
		ServerPortDescription serverPortDescription = new AServerPortDescription("localhost", "" + RELAYER_PORT, RELAYER_NAME);
		DuplexRPCClientInputPort sessionServerClientPort = DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
				"localhost", "" + ADelayingSessionsServerLauncher.SESSION_SERVER_PORT, 
				ADelayingSessionsServerLauncher.SESSION_SERVER_NAME, RELAYER_NAME);
		
		RelayerSupportingSessionServer sessionsServer =  (RelayerSupportingSessionServer) DirectedRPCProxyGenerator.generateRPCProxy(sessionServerClientPort, null, RelayerSupportingSessionServer.class, null);
		sessionServerClientPort.connect();
		sessionsServer.setRelayerDescripton(serverPortDescription);
	}
	

}
