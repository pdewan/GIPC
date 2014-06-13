package replicatedserverport.rpc.duplex.singleresponse.example;

import inputport.InputPort;
import inputport.rpc.group.example.AGroupRPCClientInputPortLauncher;
import inputport.rpc.group.example.AnOldGroupRPCClientInputPortLauncher;
import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.rpc.duplex.ReplicatedServerDuplexRPCClientPortSelector;
import replicatedserverport.rpc.duplex.singleresponse.ASingleResponseReplicatedPortLauncherSupport;
// why is a duplex port extending a group launcher
public class ASingleResponseGroupRPCDuplexClientPortLauncher 
//         extends AnOldGroupRPCClientInputPortLauncher{
	  extends AGroupRPCClientInputPortLauncher{
	
	
//	final static int SESSION_SERVER_PORT = 9090;
//	final static String SESSION_SERVER_NAME = "Session Server";
//	final static String SESSION_NAME = "Test Session";
    
	
//	protected static ServerPortDescription server1Description = new AServerPortDescription("localhost", "9090", "Server1");
//	protected static ServerPortDescription server2Description = new AServerPortDescription("localhost", "9091", "Server2");
//	protected static ServerPortDescription server3Description = new AServerPortDescription("localhost", "9092", "Server3");
//	protected static ServerPortDescription[] serversDescription = {server1Description, server2Description, server3Description};
	protected static SessionParticipantDescription[] serversDescription = {
		GroupRPCServer1Launcher.SERVER_1_DESCRIPTION,
		GroupRPCServer2Launcher.SERVER_2_DESCRIPTION,
		GroupRPCServer3Launcher.SERVER_3_DESCRIPTION};

//	String id, name;
//	boolean addConnectListener, addReplyingReceiveListener, greetOnReadingInput;
	public ASingleResponseGroupRPCDuplexClientPortLauncher(String aName) {
		super(aName);
	
		
	}
	
	
//	protected  InputPort createPort() {
//		return ReplicatedServerSessionPortSelector.
//		createObjectGroupSessionPort(serversDescription, id, name, AGroupRPCSingleResponseServerLauncher.SESSION_SERVER_NAME, SESSION_NAME);
//		
//		
//	}
	@Override
	protected InputPort getPort() {
		// how is this group rpc? it is duplex rpc
		return ReplicatedServerDuplexRPCClientPortSelector.createDuplexRPCPort(serversDescription, AGroupRPCSingleResponseServerLauncher.UPPECASE_SERVER_NAME, this.clientName, ParticipantChoice.SYMMETRIC_JOIN);
	}

	protected PortLauncherSupport getPortLauncherSupport() {
//		return new ALatecomerRelayerAndSessionServerLauncherSupport();
		return new ASingleResponseReplicatedPortLauncherSupport();

	}
	
	
	
//	@Override
//	public void createUI (InputPort anInputPort) {
//		System.out.println("-----------------------Connected to Session Port---------------------------------------");
//		GroupAllSender<Object> sessionPort = (GroupAllSender) anInputPort;
////		if (greetOnReadingInput) {			
//		       Scanner in = new Scanner(System.in);		  
////		       String message  = in.nextLine();
////		       sessionPort.sendAll(aName + " says hi to all");
//		       while (true) {
//		    	   System.out.println("Please enter  next input");
////					  in = new Scanner(System.in);		  
//				     String message  = in.nextLine();
//				      sessionPort.sendAll(message);
//				}
//		}
//		
////	}

}
