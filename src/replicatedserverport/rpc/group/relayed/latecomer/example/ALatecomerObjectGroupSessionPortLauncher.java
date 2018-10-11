package replicatedserverport.rpc.group.relayed.latecomer.example;

import java.util.Scanner;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.group.GroupAllSender;
import inputport.datacomm.simplex.buffer.example.ASimplexBufferClientInputPortLauncher;
import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.delay.DelayUtlity;
import port.delay.example.AnEchoingObjectReceiveListener;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.rpc.duplex.singleresponse.ASingleResponseReplicatedPortLauncherSupport;
import replicatedserverport.rpc.group.ReplicatedServerSessionPortSelector;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.flexible.example.AFrostySessionConnectionListener;
import sessionport.datacomm.group.object.relayed.latecomer.AnObjectGroupSessionPortLatecomerLauncherSupport;

public class ALatecomerObjectGroupSessionPortLauncher extends ASimplexBufferClientInputPortLauncher{
//	final static int SESSION_SERVER_PORT = 9090;
//	final static String SESSION_SERVER_NAME = "Session Server";
	final static String SESSION_NAME = "Test Session";
    
	
//	protected static SessionParticipantDescription server1Description = new ASessionParticipantDescription("localhost", "9090", "Server1");
//	protected static SessionParticipantDescription server2Description = new ASessionParticipantDescription("localhost", "9091", "Server2");
//	protected static SessionParticipantDescription server3Description = new ASessionParticipantDescription("localhost", "9092", "Server3");
//	protected static SessionParticipantDescription[] serversDescription = {server1Description, server2Description, server3Description};
	protected static SessionParticipantDescription[] serversDescription = {
		LatecomerSessionServer1Launcher.SERVER_1_DESCRIPTION,
		LatecomerSessionServer2Launcher.SERVER_2_DESCRIPTION,
		LatecomerSessionServer3Launcher.SERVER_3_DESCRIPTION};

	String id, name;
//	boolean addConnectListener, addReplyingReceiveListener, greetOnReadingInput;
	public ALatecomerObjectGroupSessionPortLauncher(String anId, String aName) {
		super(aName);
		id = anId;
		name = aName;
		
	}
	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
//		return new AFrostyConnectionListener((GroupSessionPort) anInputPort);
		return new AFrostySessionConnectionListener(SESSION_NAME, (GroupSessionPort) anInputPort);

		
		
	}
	protected  ReceiveListener getReceiveListener (InputPort anInputPort) {
			return new AnEchoingObjectReceiveListener();
		
	}
	
//	public  InputPort createPort() {
//		DuplexRPCClientInputPort sessionManagerClientPort = 
//			ReplicatedServerDuplexRPCClientPortSelector.createDuplexRPCPort(serversDescription, 
//					ALatecomerSessionServerLauncher.SESSION_SERVER_NAME, 
//					name);
////		DuplexSingleResponseSetter.supportSingleResponse(sessionManagerClientPort);		
//		GroupSessionPort<Object> sessionPort = ObjectGroupSessionPortSelector.createObjectGroupSessionPort(sessionManagerClientPort, SESSION_NAME, id, name);
//		return sessionPort;
//		
//	}
	
	// ReplicatedServerSesssionPortSelector may well be a vestigal
	// piece of code. It does not bind a proper bbport to object port
	// the support is simgle response so why not use single response
	// port creator?
	// actually it is ok, the launcher support was an issue
	// it was assigned to portLauncher support and not replicated port launcher support
	protected  InputPort getPort() {
		return ReplicatedServerSessionPortSelector.
		createObjectGroupSessionPort(serversDescription, id, name, 
				ALatecomerSessionServerLauncher.SESSION_SERVER_NAME, SESSION_NAME, ParticipantChoice.MEMBER);
		
		
	}
//	public  InputPort createPort() {
//		ObjectGroupSessionPortSelector.createObjectGroupSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName);
//		DuplexRPCClientInputPort sessionManagerClientPort = 
//			ReplicatedServerDuplexRPCClientPortSelector.createDuplexRPCPort(serversDescription, 
//					ALatecomerSessionServerLauncher.SESSION_SERVER_NAME, 
//					name);
////		DuplexSingleResponseSetter.supportSingleResponse(sessionManagerClientPort);		
//		GroupSessionPort<Object> sessionPort = ObjectGroupSessionPortSelector.createObjectGroupSessionPort(sessionManagerClientPort, SESSION_NAME, id, name);
//		return sessionPort;
//		
//	}
	@Override
	protected void setStateBeforePortCreation() {
//		ALatecomerRelayerAndSessionServerLauncherSupport.setLatecomerRelayedCommunicaton(true);
		DelayUtlity.setDelayClientBufferSends(true);
	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return  new AnObjectGroupSessionPortLatecomerLauncherSupport();

	}
	@Override
	protected PortLauncherSupport getReplicatedPortLauncherSupport() {
//		return new ALatecomerRelayerAndSessionServerLauncherSupport();
		return new ASingleResponseReplicatedPortLauncherSupport();

	}
	
	
	
	@Override
	public void createUI (InputPort anInputPort) {
		System.out.println("-----------------------Connected to Session Port---------------------------------------");
		GroupAllSender<Object> sessionPort = (GroupAllSender) anInputPort;
//		if (greetOnReadingInput) {			
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
		
//	}

}
