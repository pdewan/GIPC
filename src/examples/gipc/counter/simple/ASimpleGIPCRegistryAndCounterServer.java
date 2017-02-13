package examples.gipc.counter.simple;


import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleRegistryAndCounterServer;

public class ASimpleGIPCRegistryAndCounterServer  implements SimpleRegistryAndCounterServer{	
	static DistributedRMICounter counter;
	static GIPCRegistry gipcRegistry;
	static void init() {
		gipcRegistry = GIPCLocateRegistry.createRegistry(REGISTRY_PORT_NAME);
		counter = new ADistributedInheritingRMICounter();			
		gipcRegistry.rebind(COUNTER_NAME, counter);	
	}
	public static void main (String[] args) {		
		init();	
	}
}
