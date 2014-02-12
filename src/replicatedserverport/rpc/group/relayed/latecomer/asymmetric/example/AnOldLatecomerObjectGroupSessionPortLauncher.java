package replicatedserverport.rpc.group.relayed.latecomer.asymmetric.example;

import inputport.ConnectionListener;
import inputport.rpc.duplex.DuplexRPCClientInputPort;

import java.util.Scanner;

import port.ParticipantChoice;
import port.common.PortMisc;
import port.delay.DelayUtlity;
import port.delay.example.AnEchoingObjectReceiveListener;
import port.delay.example.AnObjectGroupSendingReceiveListener;
import port.sessionserver.ASessionParticipantDescription;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.relay.late.ALatecomerRelayerAndSessionServerLauncherSupport;
import replicatedserverport.rpc.duplex.ReplicatedServerDuplexRPCClientPortSelector;
import replicatedserverport.rpc.duplex.singleresponse.DuplexSingleResponseUtlity;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;
import sessionport.datacomm.group.object.example.AnObjectGroupSendingConnectListener;



public class AnOldLatecomerObjectGroupSessionPortLauncher {
	final static int SESSION_SERVER_PORT = 9090;
	final static String SESSION_SERVER_NAME = "Session Server";
	final static String SESSION_NAME = "Test Session";

	
	protected static SessionParticipantDescription server1Description = new ASessionParticipantDescription("localhost", "9090", "Server1", ParticipantChoice.SERVER_ONLY, null);
	protected static SessionParticipantDescription server2Description = new ASessionParticipantDescription("localhost", "9091", "Server2", ParticipantChoice.SERVER_ONLY, null);
	protected static SessionParticipantDescription server3Description = new ASessionParticipantDescription("localhost", "9092", "Server3", ParticipantChoice.SERVER_ONLY, null);
	protected static SessionParticipantDescription[] serversDescription = {server1Description, server2Description, server3Description};
	
	public static void launchSessionPartipant(String anId, String aName, boolean addConnectListener, boolean addReplyingReceiveListener, boolean greetOnReadingInput) {
		PortMisc.displayConnections();
	//		Tracer.showInfo(true);
//		Tracer.setKeyWordStatus(Tracer.ALL_KEYWORDS, false);
//		Tracer.setKeyWordStatus("socketip", true);
//		Tracer.setKeyWordStatus("socketdip", true);
//		Tracer.setKeyWordStatus("sesrelaylategrpobj", true);
//		Tracer.setKeyWordStatus("repsrvdupsingleresp", true);
//		Tracer.setKeyWordStatus("repsrvgrpsingleresp", true);
//		Tracer.setKeyWordStatus("sesrelay", true);


		ALatecomerRelayerAndSessionServerLauncherSupport.setLatecomerRelayedCommunicaton(true);
		
//		GlobalState.setAnyCast(true);

		DelayUtlity.setDelayClientBufferSends(true);

//		DuplexRPCClientInputPort sessionManagerClientPort = DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
//				"localhost", "" +  
//				ALatecomerSessionsServerLauncher.SESSION_SERVER_PORT, 
//				ALatecomerSessionsServerLauncher.SESSION_SERVER_NAME, 
//				aName);
		DuplexRPCClientInputPort sessionManagerClientPort = 
			ReplicatedServerDuplexRPCClientPortSelector.createDuplexRPCPort(serversDescription, SESSION_SERVER_NAME, aName, ParticipantChoice.MEMBER);
		DuplexSingleResponseUtlity.supportSingleResponse(sessionManagerClientPort);
		
		
		GroupSessionPort<Object> sessionPort = ObjectGroupSessionPortSelector.createObjectGroupSessionPort(sessionManagerClientPort, SESSION_NAME, anId, aName, ParticipantChoice.MEMBER);
//		GlobalState.doCausalBroadcast(sessionPort);	
		if (addConnectListener) {
			ConnectionListener connectListener = new AnObjectGroupSendingConnectListener(sessionPort);
		sessionPort.addConnectionListener(connectListener);
		}
		if (addReplyingReceiveListener)
			sessionPort.addReceiveListener(new AnObjectGroupSendingReceiveListener(sessionPort));
		else
			sessionPort.addReceiveListener(new AnEchoingObjectReceiveListener());

		sessionPort.connect();
		System.out.println("-----------------------Connected to Session Port---------------------------------------");
		
		if (greetOnReadingInput) {			
		       Scanner in = new Scanner(System.in);		  
//		       String message  = in.nextLine();
//		       sessionPort.sendAll(aName + " says hi to all");
		       while (true) {
		    	   System.out.println("Please enter  next input");
//					  in = new Scanner(System.in);		  
				     String message  = in.nextLine();
				      sessionPort.sendAll(message);
				}
		}
		
	}

}
