package port.old;

import inputport.rpc.duplex.example.ARegisteredEchoer;
import inputport.rpc.duplex.example.AnAnotherCounter;
import inputport.rpc.duplex.example.AnUnregisteredEchoer;
import inputport.rpc.duplex.example.AnotherCounter;
import inputport.rpc.duplex.example.AnotherEchoer;
import inputport.rpc.duplex.example.DuplexCounterAndSenderAwareSummer;
import inputport.rpc.group.GroupRPCProxyGenerator;
import inputport.rpc.group.example.ACounterWithObjectValue;
import inputport.rpc.group.example.AGroupCounterAndSenderAwareSumPrinter;
import inputport.rpc.group.example.CounterWithObjectValue;

import java.util.Scanner;

import javax.swing.JOptionPane;

import port.common.PortMisc;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.relay.RelayerClientAndServerSupport;
import sessionport.rpc.group.GroupRPCSessionPort;


public class AnOldGroupRPCStaticSessionPortLauncher {
	protected static ServerPortDescription AliceDescription = new AServerPortDescription("localhost", "9100", "Alice");
	protected static ServerPortDescription BobDescription = new AServerPortDescription("localhost", "9101", "Bob");
	protected static ServerPortDescription CathyDescription = new AServerPortDescription("localhost", "9102", "Cathy");
	protected static AnotherEchoer registerdEchoer;
	protected static AnotherEchoer unregisteredEchoer;
	protected static AnotherCounter counter;
	
	public static void waitForUserToOKConnectionThroughDialogBox() {
		Object[] options = {"Connect to Static Peers"};
		JOptionPane.showOptionDialog(
			    null,
			    "Press button when all of the peers have been started",
			    "Connection dialog",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[0]);
	}
	public static void waitForUserToOKConnectionThroughConsole(Scanner in) {		
		System.out.println("Press any key to  connect:");
		String message  = in.nextLine();
		Object[] options = {"Connect to Static Peers"};
		JOptionPane.showOptionDialog(
			    null,
			    "Press button when all of the peers have been started",
			    "Connection dialog",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[0]);
	}
	
//	public static void displayConnections() {
//		ConnectionEventListener connectionManager = new AConnectionEventManager();
//		DistEventsBus.addConnectionEventListener(connectionManager);
//		ObjectEditor.edit(connectionManager);
//	}
	
	public static void registerMethods(GroupRPCSessionPort rpcPort) {		
		DuplexCounterAndSenderAwareSummer adder = new AGroupCounterAndSenderAwareSumPrinter(rpcPort);
		rpcPort.register(DuplexCounterAndSenderAwareSummer.class, adder);
		registerdEchoer = new ARegisteredEchoer();
		rpcPort.register(AnotherEchoer.class, registerdEchoer);		
		counter = new AnAnotherCounter();
		counter = new ACounterWithObjectValue();
		rpcPort.register(CounterWithObjectValue.class, counter);
		rpcPort.register(AnotherCounter.class, counter);
	}
	

	public static void launchStaticSessionPartipant(ServerPortDescription[] aServerList, String anId, String aName) {
//		Tracer.showInfo(true);
//		ConnectionEventListener connectionManager = new AConnectionEventManager();
//		DistEventsBus.addConnectionEventListener(connectionManager);
//		ObjectEditor.edit(connectionManager);
		System.out.println("Race conditions galore in this example because of calls to getLastSender() by the registered methods");
		PortMisc.displayConnections();
		RelayerClientAndServerSupport.setRelayedCommunicaton(false);
		GroupRPCSessionPort sessionPort = 
				null;
		// fix this later
//				GroupRPCStaticSessionPortSelector.createGroupRPCStaticSessionPort(aServerList, anId, aName, null, null
//				);	
		registerMethods(sessionPort);
//		ConnectionListener connectListener = new AGroupCallingConnectListener(sessionPort);
//		sessionPort.addConnectionListener(connectListener);
//		Adder adder = new AnAdder();
//		sessionPort.register(Adder.class, adder);
//		DuplexCounterAndSenderAwareSumComputerAndPrinter adder = new AGroupCounterAndSenderAwareSumPrinter(sessionPort);
//		sessionPort.register(DuplexCounterAndSenderAwareSumComputerAndPrinter.class, adder);
		unregisteredEchoer = new AnUnregisteredEchoer();
//		registerdEchoer = new ARegisteredEchoer();
//		sessionPort.register(Echoer.class, registerdEchoer);
		Scanner in = new Scanner(System.in);
//		Tracer.showInfo(true);
//		waitForUserToOKConnectionThroughConsole(in);

		

		DuplexCounterAndSenderAwareSummer adderProxy = (DuplexCounterAndSenderAwareSummer) 
				GroupRPCProxyGenerator.generateAllRPCProxy(sessionPort, DuplexCounterAndSenderAwareSummer.class, null);
//		counter = new ACounter();
//		counter = new ACounterWithObjectValue();
//		sessionPort.register(CounterWithObjectValue.class, counter);
//		sessionPort.register(Counter.class, counter);
//		Adder adderProxy = (Adder) GroupRPCProxyGenerator.generateAllRPCProxy(sessionPort,  DuplexCounterAndSenderAwareSumComputerAndPrinter.class, null);
		waitForUserToOKConnectionThroughDialogBox();
		sessionPort.connect();
		
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
//		System.out.println("Press any key to ask for group add");
//		message  = in.nextLine();
//		Adder adderProxy = (Adder) GroupRPCProxyGenerator.generateAllRPCProxy(sessionPort,  Adder.class, null);
//		adderProxy.printSum(5, 6);
	}
}
