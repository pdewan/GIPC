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
	public static void main (String[] args) {
		try {
			GIPCRegistry gipcRegistry = GIPCLocateRegistry.createRegistry(REGISTRY_PORT_NAME);
			DistributedRMICounter counter = new ADistributedInheritingRMICounter();			
			UnicastRemoteObject.exportObject(counter, 0);
			gipcRegistry.rebind(COUNTER_NAME, counter);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
