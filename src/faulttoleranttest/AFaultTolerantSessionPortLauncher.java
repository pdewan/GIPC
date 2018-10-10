package faulttoleranttest;

import java.util.Scanner;

import inputport.ConnectionListener;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import port.ParticipantChoice;
import port.causal.ACausalGroupSessionPortLauncherSupport;
import port.delay.DelayUtlity;
import port.delay.example.AnEchoingObjectReceiveListener;
import port.delay.example.AnObjectGroupSendingReceiveListener;
import port.sessionserver.ASessionParticipantDescription;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.relay.RelayerClientAndServerSupport;
import replicatedserverport.rpc.duplex.ReplicatedServerDuplexRPCClientPortSelector;
import replicatedserverport.rpc.duplex.singleresponse.DuplexSingleResponseUtlity;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;
import sessionport.datacomm.group.object.example.AnObjectGroupSendingConnectListener;
import util.trace.Tracer;



public class AFaultTolerantSessionPortLauncher {
	final static int SESSION_SERVER_PORT = 9090;
	final static String SESSION_SERVER_NAME = "Session Server";

	
	protected static SessionParticipantDescription server1Description = new ASessionParticipantDescription("localhost", "9090", "Server1", null);
	protected static SessionParticipantDescription server2Description = new ASessionParticipantDescription("localhost", "9091", "Server2", null);
	protected static SessionParticipantDescription server3Description = new ASessionParticipantDescription("localhost", "9092", "Server3", null);
	protected static SessionParticipantDescription[] serversDescription = {server1Description, server2Description, server3Description};
	public static void launchSessionPartipant( String anId, String aName, 
			boolean addConnectListener, boolean addReplyingReceiveListener, boolean greetOnReadingInput) {
		Tracer.showInfo(true);
		RelayerClientAndServerSupport.setRelayedCommunicaton(false);
		DelayUtlity.setDelayClientBufferSends(true);
//		GlobalState.setCausalBroadcast(true);
//		GroupSessionPort<Object> groupSessionPort = ObjectGroupStaticSessionPortSelector.createObjectGroupStaticSessionPort(serversDescription, null, aName);
//		GroupMultiServerClientPort<Object> groupMultiServerPort = ObjectGroupMultiServerPortSelector.createGroupMultiServerClientPort(serversDescription, null, aName);


//		DuplexClientInputPort<Object> sessionsServerObjectPort = new AReplicatedServerDuplexClientPort<Object>(groupMultiServerPort, SESSION_SERVER_NAME );
		

//		DuplexRPCClientInputPort sessionsServerClientPort = DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(sessionsServerObjectPort);

		DuplexRPCClientInputPort sessionsServerClientPort = 
			ReplicatedServerDuplexRPCClientPortSelector.createDuplexRPCPort(serversDescription, SESSION_SERVER_NAME, aName, ParticipantChoice.SYMMETRIC_JOIN);
		DuplexSingleResponseUtlity.supportSingleResponse(sessionsServerClientPort);

		//		GlobalState.setDelayAndCausal(true);
//		DuplexRPCClientInputPort sessionManagerClientPort = DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
//				"localhost", "" +  SESSION_SERVER_PORT, SESSION_SERVER_NAME, aName);
		
		GroupSessionPort<Object> sessionPort = ObjectGroupSessionPortSelector.createObjectGroupSessionPort(sessionsServerClientPort, "Test Session", anId, aName, ParticipantChoice.MEMBER);	
		ACausalGroupSessionPortLauncherSupport.doCausalBroadcast(sessionPort);
//		GroupSessionPort<Object> sessionPort = ObjectGroupSessionPortSelector.createObjectGroupSessionPort("localhost", 
//				"" +SESSION_SERVER_PORT, SESSION_SERVER_NAME, "Test Session", anId, aName
//				);
			
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
	    Scanner in = new Scanner(System.in);		  

		if (greetOnReadingInput) {			
		       String message  = in.nextLine();
		       sessionPort.sendAll(aName + " says hi to all");
     
		}
		while (true) {
			String message  = in.nextLine();
		    sessionPort.sendAll(aName +  message);

		}
	}

}
