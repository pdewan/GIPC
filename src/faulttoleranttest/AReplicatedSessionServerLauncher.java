package faulttoleranttest;


import port.sessionserver.ASessionServer;
import port.sessionserver.SessionServer;
import port.sessionserver.relay.RelayerSupportingSessionServer;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import replicatedserverport.rpc.duplex.singleresponse.DuplexSingleResponseUtlity;
import util.trace.Tracer;


public class AReplicatedSessionServerLauncher {
	
	public static void main (String args[]) {
			
	}
	
	public static void launchServer(String aSessionServerName, String aSessionServerPort ) {
		Tracer.showInfo(true);
		DuplexRPCServerInputPort serverInputPort = DuplexRPCInputPortSelector.createDuplexRPCServerInputPort("" + 
				aSessionServerPort, aSessionServerName);
		DuplexSingleResponseUtlity.supportSingleResponse(serverInputPort);
		SessionServer sessionServer = new ASessionServer(serverInputPort);
//		RelayerSupportingSessionsServer sessionServer = new ARelayerSupportingSessionsServer(serverInputPort);

		serverInputPort.register(RelayerSupportingSessionServer.class, sessionServer);
		serverInputPort.register(AFaultTolerantSessionPortLauncher.SESSION_SERVER_NAME, sessionServer);
		serverInputPort.connect();	
	}
	

}
