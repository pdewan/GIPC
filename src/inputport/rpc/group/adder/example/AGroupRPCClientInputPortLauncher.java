package inputport.rpc.group.adder.example;


import java.util.Scanner;

import inputport.InputPort;
import inputport.rpc.RPCRegistry;
import inputport.rpc.duplex.example.ADuplexRPCClientInputPortLauncher;
import inputport.rpc.duplex.example.ARegisteredEchoer;
import inputport.rpc.duplex.example.AnotherCounter;
import inputport.rpc.duplex.example.AnotherEchoer;
import inputport.rpc.group.AGroupRPCInputPortLauncherSupport;
import inputport.rpc.group.GroupRPCInputPortSelector;
import port.PortLauncherSupport;
public class AGroupRPCClientInputPortLauncher extends ADuplexRPCClientInputPortLauncher  {
	protected AnotherEchoer registerdEchoer;
	protected GroupCounterAndSenderAwareSumPrinterAndCapitalizer adderProxy;


	public AGroupRPCClientInputPortLauncher(String aClientName) {
		super(aClientName);
	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return new AGroupRPCInputPortLauncherSupport();
	}
	@Override
	protected InputPort getPort() {
		return GroupRPCInputPortSelector.createDuplexRPCClientInputPort(
				SERVER_HOST, SERVER_ID, SERVER_NAME, 	clientName);
	}
	@Override
	protected AnotherCounter createAndRegisterCounter(RPCRegistry aClientInputPort) {
		AnotherCounter counter = new ACounterWithObjectValue();
//		aClientInputPort.register(CounterWithObjectValue.class, counter);
//		aClientInputPort.register(Counter.class, counter);
		aClientInputPort.register(counter);
//		aClientInputPort.register(Counter.class, counter);
		return counter;
	}	
	protected void registerRemoteObjects()  {
		RPCRegistry anRPCRegistry = (RPCRegistry) mainPort;
		registerdEchoer = createAndRegisterEchoer(anRPCRegistry);
		unregisteredEchoer = createUnregisteredEchoer(anRPCRegistry); //does not stricltly belong in this method
		counter = createAndRegisterCounter(anRPCRegistry);
		
	}
	protected AnotherEchoer createAndRegisterEchoer(RPCRegistry aClientInputPort) {
		AnotherEchoer echoer = new ARegisteredEchoer();
		aClientInputPort.register(echoer);
		return echoer;
	}
	@Override
	protected void createProxies() {
		rpcProxyGenerator = duplexRPCClientInputPort().getRPCProxyGenerator();

		 adderProxy = (GroupCounterAndSenderAwareSumPrinterAndCapitalizer) 
				   rpcProxyGenerator.generateRPCProxy(null, AGroupCounterAndSenderAwareSumPrinter.class, null);
	}
protected void createUI (InputPort aClientInputPort) {
		
//		RPCProxyGenerator rpcProxyGenerator = duplexRPCClientInputPort().getRPCProxyGenerator();
//		DuplexCounterAndSenderAwareSumComputerAndPrinter adderProxy = (DuplexCounterAndSenderAwareSumComputerAndPrinter) StaticRPCProxyGenerator.generateRPCProxy(aClientInputPort, null, DuplexCounterAndSenderAwareSumComputerAndPrinter.class, null);
//		DuplexCounterAndSenderAwareSumComputerAndPrinter adderProxy = (DuplexCounterAndSenderAwareSumComputerAndPrinter) 
//		   rpcProxyGenerator.generateRPCProxy(null, DuplexCounterAndSenderAwareSumComputerAndPrinter.class, null);

		String stringMessage1;
		Scanner in = new Scanner(System.in);	
		while (true) {
			System.out.println("Please enter an excerpt from Frost's Stopping by Woods on a Snowy Evening");
		    stringMessage1  = in.nextLine();
//			System.out.println("Got input");

//		    stringMessage2 = in.nextLine();
		    counter.increment(1);
		    try {
		    	// synchronous
//		    	Object retVal = adderProxy.sum(stringMessage1, stringMessage2);
//		    	System.out.println("Remote sum:" + retVal);
		    	// sync or async depending on RPC
//		    	adderProxy.printSum(stringMessage1, stringMessage2);
		    	// sync or async depending on RPC, sync will cause deadlock
//		    	System.out.println("Sending echoer:" + unregisteredEchoer);
		    	adderProxy.capitalizeAndCallbackCounter(unregisteredEchoer, stringMessage1);
//		    	printSumAndCallBackProcedureAndFunction(unregisteredEchoer, stringMessage1, stringMessage2);
		    	
		    			
		    } catch (Exception e) {
		    		e.printStackTrace();
		    }
		}
	}	


}
