package sessionport.datacomm.group.object.flexible.example;

import port.ParticipantChoice;
import port.sessionserver.ASessionServerLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;

public class AnAliceGroupSessionPort {
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
		(new AFlexibleSessionPortClientLauncher(AFlexibleSessionPortClientLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, ASessionServerLauncher.SESSION_SERVER_NAME, 
				"9100", 
				"Alice",
				AFlexibleSessionPortClientLauncher.SESSION_CHOICE,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.AnAliceDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL, ParticipantChoice.SYMMETRIC_JOIN
				)).launch();
	}
	
	

}
