package examples.gipc.counter.simple;


import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import port.ATracingConnectionListener;
import util.trace.port.buffer.BufferTraceUtility;
import util.trace.port.rpc.RPCTraceUtility;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleRegistryAndCounterServer;

public class ASimpleGIPCRegistryAndCounterServer  implements SimpleRegistryAndCounterServer{	
	protected static DistributedRMICounter counter;
	protected static GIPCRegistry gipcRegistry;
	protected static void init() {
		gipcRegistry = GIPCLocateRegistry.createRegistry(SERVER_PORT);
		counter = new ADistributedInheritingRMICounter();			
		gipcRegistry.rebind(COUNTER_NAME, counter);	
		gipcRegistry.getInputPort().addConnectionListener(new ATracingConnectionListener(gipcRegistry.getInputPort()));
	}
	public static void main (String[] args) {		
//		BufferTraceUtility.setTracing();
//		RPCTraceUtility.setTracing();
		init();
	}
}
