package replicatedsessionport.rpc.duplex.singleresponse.example;

import inputport.InputPort;
import inputport.rpc.RPCProxyGenerator;
import inputport.rpc.RPCRegistry;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.example.ARegisteredEchoer;
import inputport.rpc.duplex.example.AnUnregisteredEchoer;
import inputport.rpc.duplex.example.AnotherCounter;
import inputport.rpc.duplex.example.AnotherEchoer;
import inputport.rpc.group.adder.example.ACounterWithObjectValue;
import inputport.rpc.group.adder.example.AGroupCounterAndSenderAwareSumPrinter;
import inputport.rpc.group.adder.example.GroupCounterAndSenderAwareSumPrinterAndCapitalizer;

import java.util.Scanner;

import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;

import replicatedserverport.rpc.group.flexibleresponse.flexible.example.ReplicationChoice;

public class ASingleResponseReplicatedGroupSessionPortClientLauncher 
	extends AJitteryFlexibleReplicatedGroupSessionPortClientLauncher{
	protected AnotherEchoer registerdEchoer;
	protected AnotherEchoer unregisteredEchoer;
	protected AnotherCounter counter;
	protected GroupCounterAndSenderAwareSumPrinterAndCapitalizer adderProxy;
	protected RPCProxyGenerator rpcProxyGenerator; 



	public ASingleResponseReplicatedGroupSessionPortClientLauncher(
			String aSessionServerHost, 
			String aServerId, 
			String aServerName, 
			String aLogicalServerName, 
			String aMyName, 
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoCausal,
			SessionParticipantDescription[] aServersDescription, boolean aDoJitter) {
		super(aSessionServerHost, aServerId, aServerName,  aLogicalServerName, aMyName, aSessionChoice, aShouldDelay, aDelaysSupport, ReplicationChoice.SINGLE_RESPONSE, aDoCausal, aServersDescription, aDoJitter);
	}

	

//	protected Counter createAndRegisterCounter(RPCRegistry aClientInputPort) {
//		Counter counter = new ACounterWithObjectValue();
//		aClientInputPort.register(counter);
//		return counter;
//	}	
		
	
	protected void registerRemoteObjects()  {
		RPCRegistry anRPCRegistry = (RPCRegistry) mainPort;
		registerdEchoer = createAndRegisterEchoer(anRPCRegistry);
		unregisteredEchoer = createUnregisteredEchoer(anRPCRegistry); //does not stricltly belong in this method
		counter = createAndRegisterCounter(anRPCRegistry);
		
	}
	protected AnotherCounter createAndRegisterCounter(RPCRegistry aClientInputPort) {
		AnotherCounter counter = new ACounterWithObjectValue();
		aClientInputPort.register(counter);
		return counter;
	}	
	protected AnotherEchoer createAndRegisterEchoer(RPCRegistry aClientInputPort) {
		AnotherEchoer echoer = new ARegisteredEchoer();
		aClientInputPort.register(echoer);
		return echoer;
	}
	
	protected AnotherEchoer createUnregisteredEchoer(RPCRegistry aClientInputPort) {
		AnotherEchoer echoer = new AnUnregisteredEchoer();
//		aClientInputPort.register(Echoer.class, echoer);
		return echoer;
	}
	protected DuplexRPCClientInputPort duplexRPCClientInputPort() {
		return ((DuplexRPCClientInputPort) mainPort);
	}
	
	@Override
	protected void createProxies() {
		rpcProxyGenerator = duplexRPCClientInputPort().getRPCProxyGenerator();

		 adderProxy = (GroupCounterAndSenderAwareSumPrinterAndCapitalizer) 
				   rpcProxyGenerator.generateRPCProxy(null, AGroupCounterAndSenderAwareSumPrinter.class, null);
	}
//	@Override
//	public void createUI (InputPort anInputPort) {
//		SimplexRPCClientInputPort aClientInputPort = (SimplexRPCClientInputPort) anInputPort;
//		String stringMessage1;
//		String stringMessage2;
////		RPCProxyGenerator rpcProxyGenerator = aClientInputPort.getRPCProxyGenerator();
//		try {
////	    	SumPrinter adderProxy = (SumPrinter) rpcProxyGenerator.generateRPCProxy(null, SumPrinter.class, null);
//
//			
//		
//		Scanner in = new Scanner(System.in);	
//		while (true) {
//			System.out.println("Please enter two messages to be concatenated by the server:");
//		    stringMessage1  = in.nextLine();
//		    stringMessage2 = in.nextLine();
////		    try {		    	
////		    	SumPrinter adderProxy = (SumPrinter) StaticRPCProxyGenerator.generateRPCProxy(aClientInputPort, null, SumPrinter.class, null);
////				Class adderProxyClass = adderProxy.getClass();
////				Method[] methods = adderProxyClass.getMethods();
////				Class[] argTypes = {String.class, String.class};
////				Method method = adderProxyClass.getMethod("printSum", argTypes);
////				String args[] = {stringMessage1, stringMessage2};
////				method.invoke(adderProxy, args);
//		    	adderProxy.printSum(stringMessage1, stringMessage2); 
//		    	adder
//		    	
//		    	
//		    	
////		    			
////		    } catch (Exception e) {
////		    		e.printStackTrace();
////		    }
//		}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return;
//		}
//	}
public void createUI (InputPort aClientInputPort) {
		
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
