package staticsessionport.rpc.group.example;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.datacomm.duplex.object.echoer.example.AFrostyObjectConnectionListener;
import inputport.rpc.duplex.example.DuplexCounterAndSenderAwareSummer;
import inputport.rpc.group.GroupRPCProxyGenerator;
import inputport.rpc.group.adder.example.AGroupCounterAndSenderAwareSumPrinter;
import inputport.rpc.group.adder.example.AGroupRPCClientInputPortLauncher;

import java.util.Scanner;

import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.sessionserver.ASessionParticipantDescription;
import port.sessionserver.SessionParticipantDescription;
import sessionport.rpc.group.GroupRPCSessionPort;
import staticsessionport.datacomm.group.object.example.AGroupObjectStaticSessionPortLauncher;
import staticsessionport.rpc.group.AStaticSessionGroupRPCPortLauncherSupport;
import staticsessionport.rpc.group.GroupRPCStaticSessionPortSelector;
import examples.mvc.rmi.collaborative.relaying.AnEchoer;
import examples.mvc.rmi.collaborative.relaying.Echoer;


public class AGroupRPCStaticSessionPortLauncher extends AGroupRPCClientInputPortLauncher {
	protected  static SessionParticipantDescription AliceDescription = new ASessionParticipantDescription("localhost", "9100", "Alice", null);
	protected static  SessionParticipantDescription BobDescription = new ASessionParticipantDescription("localhost", "9101", "Bob", null);
	protected static  SessionParticipantDescription CathyDescription = new ASessionParticipantDescription("localhost", "9102", "Cathy", null);
	protected static final String REMOTE_END_POINT = "Echo Servers" ; 

	protected  Echoer registerdEchoer;
//	protected  Echoer unregisteredEchoer;
//	protected  Counter counter;
	SessionParticipantDescription[] serverList;
	String id;
	String name;
	DuplexCounterAndSenderAwareSummer adderProxy;
	
	
	public AGroupRPCStaticSessionPortLauncher(SessionParticipantDescription[] aServerList, String anId, String aName) {
		super(aName);
		serverList = aServerList;
		id = anId;
		name = aName;
//		GlobalState.setRelayedCommunicaton(false);

	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return new AStaticSessionGroupRPCPortLauncherSupport();
	}
	
	@Override
	protected InputPort getPort() {
		return GroupRPCStaticSessionPortSelector.createGroupRPCStaticSessionPort(serverList, id, name, REMOTE_END_POINT, ParticipantChoice.SYMMETRIC_JOIN
				);	
	}
	@Override
	protected ConnectionListener getConnectionListener(InputPort anInputPort) {
		return new AFrostyObjectConnectionListener((DuplexInputPort<Object>) anInputPort);
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
	protected  void createProxies() {
		adderProxy = (DuplexCounterAndSenderAwareSummer) 
				GroupRPCProxyGenerator.generateAllRPCProxy((GroupRPCSessionPort) mainPort, DuplexCounterAndSenderAwareSummer.class, null);
		
	}
	@Override
	protected  void registerRemoteObjects() {	
		super.registerRemoteObjects();
		GroupRPCSessionPort aGroupRPCSessionPort = (GroupRPCSessionPort) mainPort;
		DuplexCounterAndSenderAwareSummer adder = new AGroupCounterAndSenderAwareSumPrinter(aGroupRPCSessionPort);
		aGroupRPCSessionPort.register(DuplexCounterAndSenderAwareSummer.class, adder);
		registerdEchoer = new AnEchoer();
		aGroupRPCSessionPort.register(Echoer.class, registerdEchoer);		
//		counter = new ACounter();
//		counter = new ACounterWithObjectValue();
//		aGroupRPCSessionPort.register(CounterWithObjectValue.class, counter);
//		aGroupRPCSessionPort.register(Counter.class, counter);
	}
	

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
	
	@Override
	public void launch() {
		createAndBindConnectablePorts();
		AGroupObjectStaticSessionPortLauncher.waitForUserToOKConnectionThroughDialogBox();
		mainPort.connect();
		createUI(mainPort);

	}
	
	public  void createUI(InputPort anInputPort) {

		System.out.println("Race conditions galore in this example because of calls to getLastSender() by the registered methods");
//		PortMisc.displayConnections();
//		GlobalState.setRelayedCommunicaton(false);
//		GroupRPCSessionPort sessionPort = GroupRPCStaticSessionPortSelector.createGroupRPCStaticSessionPort(aServerList, anId, aName
//				);	
//		GroupRPCSessionPort sessionPort = (GroupRPCSessionPort) inputPort;
//		registerMethods(sessionPort);

//		unregisteredEchoer = new AnUnregisteredEchoer();

		Scanner in = new Scanner(System.in);


		

//		DuplexCounterAndSenderAwareSumComputerAndPrinter adderProxy = (DuplexCounterAndSenderAwareSumComputerAndPrinter) 
//				GroupRPCProxyGenerator.generateAllRPCProxy(sessionPort, DuplexCounterAndSenderAwareSumComputerAndPrinter.class, null);

//		waitForUserToOKConnectionThroughDialogBox();
//		sessionPort.connect();
		
		while (true) {
			System.out.println("Please enter two messages:");
		    String stringMessage1  = in.nextLine();
		    String stringMessage2 = in.nextLine();
		    counter.increment(1);
		    try {
		    	// synchronous
		    	Object[] retVal = (Object[]) adderProxy.sum(stringMessage1, stringMessage2);
		    	for (Object val:retVal) {
			    	System.out.println("Remote sum:" + val);
		    	}
//		    	// sync or async depending on RPC
		    	adderProxy.printUppercase(stringMessage1);
//		    	// sync or async depending on RPC, sync will cause deadlock
		    	// lots of messages being sent. Race conditions determine what last sender is when call others is invoked
		    	adderProxy.printSumAndCallBackProcedureAndFunction(unregisteredEchoer, stringMessage1, stringMessage2);
		    			
		    } catch (Exception e) {
		    		e.printStackTrace();
		    }
		}

	}
}
