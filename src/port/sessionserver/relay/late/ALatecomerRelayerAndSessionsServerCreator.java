package port.sessionserver.relay.late;

import inputport.rpc.group.GroupRPCInputPortSelector;
import inputport.rpc.group.GroupRPCServerInputPort;
import port.delay.DelayUtlity;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.ServerPortDescription;
import replicatedserverport.rpc.duplex.singleresponse.SingleResponseReplicatedClientServerUtlity;
import util.trace.Tracer;


public class ALatecomerRelayerAndSessionsServerCreator {
	public static int SESSION_SERVER_PORT = 9090;
	public static String SESSION_SERVER_NAME = "Session Server";
	public static int RELAYER_PORT = 9091;
	public static String RELAYER_NAME = "Relayer";
	

	public static void main (String args[]) {

	}
	public static void createServerWithTracingAndDelays(String aSessionsServerName, String aSessionServerPort) {
//		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(Tracer.ALL_KEYWORDS, false);
		Tracer.setKeywordPrintStatus("socketip", true);
		Tracer.setKeywordPrintStatus("socketdip", true);
		Tracer.setKeywordPrintStatus("sesrelaylategrpobj", true);
		Tracer.setKeywordPrintStatus("repsrvdupsingleresp", true);
		Tracer.setKeywordPrintStatus("repsrvgrpsingleresp", true);
		Tracer.setKeywordPrintStatus("sesrelaylate", true);
		DelayUtlity.setDelayServerBufferSends();
		DelayUtlity.getDelayManager().setMinimumDelay("Alice", 1000);
		DelayUtlity.getDelayManager().setMinimumDelay("Bob", 100);
		DelayUtlity.getDelayManager().setMinimumDelay("Cathy", 500);
		createServer(aSessionsServerName, aSessionServerPort);
	}
	public static GroupRPCServerInputPort createServer(String aSessionsServerName, String aSessionServerId) {		
		GroupRPCServerInputPort serverInputPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort( 
				aSessionServerId, aSessionsServerName);
		SingleResponseReplicatedClientServerUtlity.supportSingleResponse(serverInputPort);
		LocalLatecomerSessionsServer sessionsServer = new ALatecomerSessionServer(serverInputPort);
		serverInputPort.register(LatecomerSessionServer.class, sessionsServer);
		serverInputPort.register(aSessionsServerName, sessionsServer);
		Tracer.info(sessionsServer, "Created and registered Latecomer session server object connected to server port " + serverInputPort + " for " + aSessionServerId );
		GroupRPCServerInputPort relayPort = serverInputPort;
		LatecomerRelayer relayer = new ALatecomerRelayer(relayPort, sessionsServer);
		relayPort.register(LatecomerRelayer.class, relayer);
		Tracer.info(sessionsServer, "Created and registered relayer  object connected to server port " + serverInputPort);
//		relayPort.register(aSessionsServerName, relayer);		
		ServerPortDescription relayerPortDescription = new AServerPortDescription("localhost", "" + aSessionServerId, aSessionsServerName);		
		Tracer.info(sessionsServer, "Registering relayer description " + relayerPortDescription + " with session server");
		sessionsServer.setRelayerDescripton(relayerPortDescription);

		serverInputPort.connect();	
		return serverInputPort;
	}
	// no real difference between the two methods except the name of the relayers, what is going on
	public static GroupRPCServerInputPort createLatecomerSessionsServerAndRelayer(String aSessionServerId, String aSessionsServerName, String aLogicalServerName) {		
		GroupRPCServerInputPort serverInputPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort( 
				aSessionServerId, aSessionsServerName);
		LocalLatecomerSessionsServer sessionsServer = new ALatecomerSessionServer(serverInputPort);
		serverInputPort.register(LatecomerSessionServer.class, sessionsServer);
		serverInputPort.register(aLogicalServerName, sessionsServer);
		Tracer.info("Created and registered Latecomer session server object connected to server port " + serverInputPort + " for " + aSessionServerId );
		GroupRPCServerInputPort relayPort = serverInputPort;
		LatecomerRelayer relayer = new ALatecomerRelayer(relayPort, sessionsServer);
		relayPort.register(LatecomerRelayer.class, relayer);
		relayPort.register(RELAYER_NAME, relayer);
		Tracer.info("Created and registered relayer  object connected to server port " + serverInputPort);
		ServerPortDescription relayerPortDescription = new AServerPortDescription("localhost", "" + aSessionServerId, RELAYER_NAME);		
		sessionsServer.setRelayerDescripton(relayerPortDescription);		
		serverInputPort.connect();	
		return serverInputPort;
	}
}
