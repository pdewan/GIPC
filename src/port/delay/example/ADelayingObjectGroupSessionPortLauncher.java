package port.delay.example;

import java.util.Scanner;

import inputport.ConnectionListener;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import port.ParticipantChoice;
import port.causal.ACausalGroupSessionPortLauncherSupport;
import port.delay.DelayUtlity;
import port.sessionserver.relay.RelayerClientAndServerSupport;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;
import sessionport.datacomm.group.object.example.AnObjectGroupSendingConnectListener;


public class ADelayingObjectGroupSessionPortLauncher {
	public static void launchSessionPartipant( String anId, String aName, boolean addConnectListener, boolean addReplyingReceiveListener, boolean greetOnReadingInput) {
//		Tracer.showInfo(true);
//		GlobalState.setRelayedCommunicaton(true);
		RelayerClientAndServerSupport.setEfficientRelayedCommunicaton(true);

		DelayUtlity.setDelayClientBufferSends(true);
//		GlobalState.setCausalBroadcast(true);
//		GlobalState.setDelayAndCausal(true);
		DuplexRPCClientInputPort sessionManagerClientPort = DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
				"localhost", "" +  
				ADelayingRelayingSessionsServerLauncher.SESSION_SERVER_PORT, 
				ADelayingRelayingSessionsServerLauncher.SESSION_SERVER_NAME, 
				aName);
		GroupSessionPort<Object> sessionPort = ObjectGroupSessionPortSelector.createObjectGroupSessionPort(sessionManagerClientPort, "Test Session", anId, aName, ParticipantChoice.MEMBER);
		ACausalGroupSessionPortLauncherSupport.doCausalBroadcast(sessionPort);
//		GroupSessionPort<Object> sessionPort = ObjectGroupSessionPortSelector.createObjectGroupSessionPort("localhost", 
//				"" + ADelayingRelayingSessionsServerLauncher.SESSION_SERVER_PORT,
//				ADelayingRelayingSessionsServerLauncher.SESSION_SERVER_NAME, "Test Session", anId, aName
//				);
//		GlobalState.attachDelaySendTrapper(sessionPort)	;
//		DuplexServerInputPort<ByteBuffer> sessionPort =	new ADuplexBufferSessionPortFullP2P("localhost", 
//				"" + ASessionsServer.SESSION_SERVER_PORT, ASessionsServer.SESSION_SERVER_NAME, "Test Session", anId, aName);
//		PrintingReplyingReceiveListener printingReplyingReceiveListener = new PrintingReplyingReceiveListener(sessionPort);	
		if (addConnectListener) {
			ConnectionListener connectListener = new AnObjectGroupSendingConnectListener(sessionPort);
//		sessionPort.addConnectListener(printingReplyingReceiveListener);
		sessionPort.addConnectionListener(connectListener);
		}
		if (addReplyingReceiveListener)
			sessionPort.addReceiveListener(new AnObjectGroupSendingReceiveListener(sessionPort));
		else
			sessionPort.addReceiveListener(new AnEchoingObjectReceiveListener());
//		serverInputPort.addDisconnectListener(echoingReceiveListener);
//		sessionPort.addReceiveListener(printingReplyingReceiveListener);	
		sessionPort.connect();
		System.out.println("-----------------------Connected to Session Port---------------------------------------");
		
		if (greetOnReadingInput) {			
		       Scanner in = new Scanner(System.in);		  
		       String message  = in.nextLine();
		       sessionPort.sendAll(aName + " says hi to all");
		       while (true) {
					  in = new Scanner(System.in);		  
				      message  = in.nextLine();
				      sessionPort.sendAll(message);
				}
		}
		
	}

}
