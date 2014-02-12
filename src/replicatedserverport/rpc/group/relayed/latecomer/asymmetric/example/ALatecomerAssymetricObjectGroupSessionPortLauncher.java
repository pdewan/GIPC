package replicatedserverport.rpc.group.relayed.latecomer.asymmetric.example;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.group.GroupAllSender;
import inputport.datacomm.simplex.buffer.example.ASimplexBufferClientInputPortLauncher;
import inputport.rpc.duplex.DuplexRPCClientInputPort;

import java.util.Scanner;

import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.common.PortMisc;
import port.delay.DelayUtlity;
import port.delay.example.AnEchoingObjectReceiveListener;
import port.delay.example.AnObjectGroupSendingReceiveListener;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.relay.late.ALatecomerRelayerAndSessionServerLauncherSupport;
import replicatedserverport.rpc.duplex.ReplicatedServerDuplexRPCClientPortSelector;
import replicatedserverport.rpc.duplex.singleresponse.ASingleResponseReplicatedPortLauncherSupport;
import replicatedserverport.rpc.duplex.singleresponse.DuplexSingleResponseUtlity;
import replicatedserverport.rpc.group.relayed.latecomer.example.LatecomerSessionServer1Launcher;
import replicatedserverport.rpc.group.relayed.latecomer.example.LatecomerSessionServer2Launcher;
import replicatedserverport.rpc.group.relayed.latecomer.example.LatecomerSessionServer3Launcher;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;
import sessionport.datacomm.group.object.example.AnObjectGroupSendingConnectListener;

public class ALatecomerAssymetricObjectGroupSessionPortLauncher extends ASimplexBufferClientInputPortLauncher{
	final static int SESSION_SERVER_PORT = 9090;
	final static String SESSION_SERVER_NAME = "Session Server";
	final static String SESSION_NAME = "Test Session";
    
	
//	protected static ServerPortDescription server1Description = new AServerPortDescription("localhost", "9090", "Server1");
//	protected static ServerPortDescription server2Description = new AServerPortDescription("localhost", "9091", "Server2");
//	protected static ServerPortDescription server3Description = new AServerPortDescription("localhost", "9092", "Server3");
//	protected static ServerPortDescription[] serversDescription = {server1Description, server2Description, server3Description};
	protected static SessionParticipantDescription[] serversDescription = {
		LatecomerSessionServer1Launcher.SERVER_1_DESCRIPTION,
		LatecomerSessionServer2Launcher.SERVER_2_DESCRIPTION,
		LatecomerSessionServer3Launcher.SERVER_3_DESCRIPTION};

	String id, name;
	boolean addConnectListener, addReplyingReceiveListener, greetOnReadingInput;
	public ALatecomerAssymetricObjectGroupSessionPortLauncher(String anId, String aName, 
		   boolean anAddConnectListener, boolean anAddReplyingReceiveListener, boolean aGreetOnReadingInput) {
		super(aName);
		id = anId;
		name = aName;
		addConnectListener = anAddConnectListener;
		addReplyingReceiveListener = anAddReplyingReceiveListener;
		greetOnReadingInput = aGreetOnReadingInput;
	}
	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
		if (addConnectListener) {
			return  new AnObjectGroupSendingConnectListener((GroupSessionPort) anInputPort);
		} else
			return null;
		
	}
	protected  ReceiveListener getReceiveListener (InputPort anInputPort) {
		if (addReplyingReceiveListener)
			return new AnObjectGroupSendingReceiveListener((GroupSessionPort) anInputPort);
		else
			return new AnEchoingObjectReceiveListener();
	}
	
	protected  InputPort getPort() {
		DuplexRPCClientInputPort sessionManagerClientPort = 
			ReplicatedServerDuplexRPCClientPortSelector.createDuplexRPCPort(serversDescription, SESSION_SERVER_NAME, name, ParticipantChoice.SYMMETRIC_JOIN);
//		DuplexSingleResponseSetter.supportSingleResponse(sessionManagerClientPort);		
		GroupSessionPort<Object> sessionPort = ObjectGroupSessionPortSelector.createObjectGroupSessionPort(sessionManagerClientPort, SESSION_NAME, id, name, ParticipantChoice.MEMBER);
		return sessionPort;
		
	}
	@Override
	protected void setStateBeforePortCreation() {
		ALatecomerRelayerAndSessionServerLauncherSupport.setLatecomerRelayedCommunicaton(true);
		DelayUtlity.setDelayClientBufferSends(true);
	}
	protected PortLauncherSupport getPortLauncherSupport() {
//		return new ALatecomerRelayerAndSessionServerLauncherSupport();
		return new ASingleResponseReplicatedPortLauncherSupport();

	}
	
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
			ReplicatedServerDuplexRPCClientPortSelector.createDuplexRPCPort(serversDescription, SESSION_SERVER_NAME, aName, ParticipantChoice.SYMMETRIC_JOIN);
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
	
	@Override
	public void createUI (InputPort anInputPort) {
		System.out.println("-----------------------Connected to Session Port---------------------------------------");
		GroupAllSender<Object> sessionPort = (GroupAllSender) anInputPort;
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
