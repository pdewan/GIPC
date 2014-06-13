package multiserverport.rpc.group.example;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.datacomm.duplex.object.echoer.example.AFrostyObjectConnectionListener;
import inputport.rpc.duplex.example.ARegisteredEchoer;
import inputport.rpc.duplex.example.AnotherEchoer;
import inputport.rpc.duplex.example.DuplexCounterAndSenderAwareSummer;
import inputport.rpc.group.GroupRPCProxyGenerator;
import inputport.rpc.group.GroupRPCServerInputPort;
import inputport.rpc.group.example.AGroupCounterAndSenderAwareSumPrinter;
import inputport.rpc.group.example.AnOldGroupRPCClientInputPortLauncher;

import java.util.Scanner;

import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.old.Adder;
import port.old.AnAdder;
import port.sessionserver.ASessionParticipantDescription;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.relay.RelayerClientAndServerSupport;
import sessionport.rpc.group.AGroupCallingConnectListener;
import sessionport.rpc.group.GroupRPCSessionPort;
import staticsessionport.datacomm.group.object.example.AGroupObjectStaticSessionPortLauncher;
import staticsessionport.rpc.group.AStaticSessionGroupRPCPortLauncherSupport;
import staticsessionport.rpc.group.GroupRPCStaticSessionPortSelector;
import examples.mvc.rmi.collaborative.relaying.AnEchoer;
import examples.mvc.rmi.collaborative.relaying.Echoer;


public class AGroupRPCMultiServerClientPortLauncher extends AnOldGroupRPCClientInputPortLauncher {
	protected static SessionParticipantDescription server1Description = 
			new ASessionParticipantDescription("localhost", Server1GroupRPCMSPServerLauncher.server1Id, AGroupRPCServer1Launcher.server1Name, ParticipantChoice.MEMBER);
		protected static SessionParticipantDescription server2Description = 
			new ASessionParticipantDescription("localhost", Server2GroupRPCMSPServerLauncher.server2Id, AGroupRPCServer2Launcher.server2Name, ParticipantChoice.MEMBER);
		protected static SessionParticipantDescription[] servers = {server1Description, server2Description};
		protected static final String REMOTE_END_POINT = "Echo Servers" ; 

//
	
	
	public AGroupRPCMultiServerClientPortLauncher(String aName) {
		super(aName);		

	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return new AStaticSessionGroupRPCPortLauncherSupport();
	}
	
	@Override
	protected InputPort getPort() {
		RelayerClientAndServerSupport.setRelayedCommunicaton(false);

//		return GroupRPCStaticSessionPortSelector.createGroupRPCStaticSessionPort(servers, REMOTE_END_POINT, clientName, "Add Servers", ParticipantChoice.SYMMETRIC_JOIN);					
		return GroupRPCStaticSessionPortSelector.createGroupRPCStaticSessionPort(servers, REMOTE_END_POINT, clientName, "Add Servers", ParticipantChoice.CLIENT_ONLY);					

	}
	@Override
	protected ConnectionListener getConnectionListener(InputPort anInputPort) {
		return   new AGroupCallingConnectListener(( GroupRPCSessionPort) anInputPort);
	}
	
//	public  void waitForUserToOKConnectionThroughDialogBox() {
//		Object[] options = {"Connect to Static Peers"};
//		JOptionPane.showOptionDialog(
//			    null,
//			    "Press button when all of the peers have been started",
//			    "Connection dialog",
//			    JOptionPane.YES_NO_OPTION,
//			    JOptionPane.QUESTION_MESSAGE,
//			    null,
//			    options,
//			    options[0]);
//	}
//	public  void waitForUserToOKConnectionThroughConsole(Scanner in) {		
//		System.out.println("Press any key to  connect:");
//		String message  = in.nextLine();
//		Object[] options = {"Connect to Static Peers"};
//		JOptionPane.showOptionDialog(
//			    null,
//			    "Press button when all of the peers have been started",
//			    "Connection dialog",
//			    JOptionPane.YES_NO_OPTION,
//			    JOptionPane.QUESTION_MESSAGE,
//			    null,
//			    options,
//			    options[0]);
//	}
	
//	public static void displayConnections() {
//		ConnectionEventListener connectionManager = new AConnectionEventManager();
//		DistEventsBus.addConnectionEventListener(connectionManager);
//		ObjectEditor.edit(connectionManager);
//	}
	
//	@Override
//	// why is this needed?
//	protected  void registerRemoteObjects() {	
//		Adder adder = new AnAdder();
//		((GroupRPCSessionPort) mainPort).register(Adder.class, adder);
//	}
	

//	public  void launchStaticSessionPartipant(SessionParticipantDescription[] aServerList, String anId, String aName) {
//
//		System.out.println("Race conditions galore in this example because of calls to getLastSender() by the registered methods");
//		PortMisc.displayConnections();
//		GlobalState.setRelayedCommunicaton(false);
//		GroupRPCSessionPort sessionPort = GroupRPCStaticSessionPortSelector.createGroupRPCStaticSessionPort(aServerList, anId, aName, null
//				);	
//		registerMethods(sessionPort);
//
//		unregisteredEchoer = new AnUnregisteredEchoer();
//
//		Scanner in = new Scanner(System.in);
//
//
//		
//
//		DuplexCounterAndSenderAwareSumComputerAndPrinter adderProxy = (DuplexCounterAndSenderAwareSumComputerAndPrinter) 
//				GroupRPCProxyGenerator.generateAllRPCProxy(sessionPort, DuplexCounterAndSenderAwareSumComputerAndPrinter.class, null);
//
//		waitForUserToOKConnectionThroughDialogBox();
//		sessionPort.connect();
//		
//		while (true) {
//			System.out.println("Please enter two messages:");
//		    String stringMessage1  = in.nextLine();
//		    String stringMessage2 = in.nextLine();
//		    counter.increment(1);
//		    try {
//		    	// synchronous
//		    	Object[] retVal = (Object[]) adderProxy.sum(stringMessage1, stringMessage2);
//		    	for (Object val:retVal) {
//			    	System.out.println("Remote sum:" + val);
//		    	}
////		    	// sync or async depending on RPC
//		    	adderProxy.printSum(stringMessage1, stringMessage2);
////		    	// sync or async depending on RPC, sync will cause deadlock
//		    	// lots of messages being sent. Race conditions determine what last sender is when call others is invoked
//		    	adderProxy.printSumAndCallBackProcedureAndFunction(unregisteredEchoer, stringMessage1, stringMessage2);
//		    			
//		    } catch (Exception e) {
//		    		e.printStackTrace();
//		    }
//		}
//
//	}
//	
	@Override
//	public void launch() {
//		createAndBindConnectablePorts();
//		AGroupObjectStaticSessionPortLauncher.waitForUserToOKConnectionThroughDialogBox();
//		mainPort.connect();
////		createUI(mainPort);
//
//	}
	public  void createUI(InputPort anInputPort) {
		
	}

	
//	public  void createUI(InputPort anInputPort) {
//
//		System.out.println("Race conditions galore in this example because of calls to getLastSender() by the registered methods");
////		PortMisc.displayConnections();
////		GlobalState.setRelayedCommunicaton(false);
////		GroupRPCSessionPort sessionPort = GroupRPCStaticSessionPortSelector.createGroupRPCStaticSessionPort(aServerList, anId, aName
////				);	
////		GroupRPCSessionPort sessionPort = (GroupRPCSessionPort) inputPort;
////		registerMethods(sessionPort);
//
////		unregisteredEchoer = new AnUnregisteredEchoer();
//
//		Scanner in = new Scanner(System.in);
//
//
//		
//
////		DuplexCounterAndSenderAwareSumComputerAndPrinter adderProxy = (DuplexCounterAndSenderAwareSumComputerAndPrinter) 
////				GroupRPCProxyGenerator.generateAllRPCProxy(sessionPort, DuplexCounterAndSenderAwareSumComputerAndPrinter.class, null);
//
////		waitForUserToOKConnectionThroughDialogBox();
////		sessionPort.connect();
//		
//		while (true) {
//			System.out.println("Please enter two messages:");
//		    String stringMessage1  = in.nextLine();
//		    String stringMessage2 = in.nextLine();
//		    counter.increment(1);
//		    try {
//		    	// synchronous
//		    	Object[] retVal = (Object[]) adderProxy.sum(stringMessage1, stringMessage2);
//		    	for (Object val:retVal) {
//			    	System.out.println("Remote sum:" + val);
//		    	}
////		    	// sync or async depending on RPC
//		    	adderProxy.printUppercase(stringMessage1);
////		    	// sync or async depending on RPC, sync will cause deadlock
//		    	// lots of messages being sent. Race conditions determine what last sender is when call others is invoked
//		    	adderProxy.printSumAndCallBackProcedureAndFunction(unregisteredEchoer, stringMessage1, stringMessage2);
//		    			
//		    } catch (Exception e) {
//		    		e.printStackTrace();
//		    }
//		}
//
//	}
}
