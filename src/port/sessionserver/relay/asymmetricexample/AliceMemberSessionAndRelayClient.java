package port.sessionserver.relay.asymmetricexample;

import port.ParticipantChoice;
import port.sessionserver.ASessionServerLauncher;
import port.sessionserver.asymmetricexample.ASessionServerClientLauncher;

public class AliceMemberSessionAndRelayClient {
//	public static void launch (String myHost, String myID, String myName) {
//		SessionObserver observer = new APrintingSessionObserver();
//		SessionClientDescription sessionClientDescription = new ASessionClientDescription(myHost, myID, myName);
//		DuplexRPCClientInputPort clientInputPort = DuplexRPCInputPortSelector.createDuplexRPCClientInputPort("localhost", 
//				"" + ASessionServer.SESSION_SERVER_PORT, ASessionServer.SESSION_SERVER_NAME);		
//		clientInputPort.connect();
//		try {
//			SessionServer sessionServerProxy = (SessionServer) UniRPCProxyGenerator.generateUniRPCProxy(clientInputPort,
//					SessionServer.class, null);
//			sessionServerProxy.join("Test Session", sessionClientDescription, observer);		
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
//
//	}
	public static void main (String[] args) {
		(new ASessionAndRelayServerClientLauncher(ASessionServerClientLauncher.SESSION_SERVER_HOST, 
				ASessionServerLauncher.SESSION_SERVER_ID, 
				ASessionServerLauncher.SESSION_SERVER_NAME, 
//				ASessionAndRelayServerClientLauncher.RELAYER_HOST, 
//				ARelayerLauncher.RELAYER_ID, 
//				ARelayerLauncher.RELAYER_NAME, 
				"9100", "Alice", ParticipantChoice.MEMBER)).launch();
	}
	
	

}
