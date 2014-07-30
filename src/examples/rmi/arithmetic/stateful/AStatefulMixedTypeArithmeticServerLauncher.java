package examples.rmi.arithmetic.stateful;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import util.annotations.Tags;
import util.tags.DistributedTags;
@Tags({DistributedTags.RMI, DistributedTags.SERVER})
public class AStatefulMixedTypeArithmeticServerLauncher implements MixedTypeAirthmeticServer {	
	public static void main (String[] args) {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			RemoteStatefulMixedTypeProcessor mixedTypeProcessor = new AStatefulMixedTypeProcessor();
			//create proxy
			UnicastRemoteObject.exportObject(mixedTypeProcessor, 0);	
			// make it available for clients
			rmiRegistry.rebind(SERVER_NAME, mixedTypeProcessor);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
