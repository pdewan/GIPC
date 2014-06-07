package examples.rmi.arithmetic;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import util.annotations.Tags;
@Tags({"RMIServer"})
public class AMixedTypeArithmeticServerLauncher implements MixedTypeAirthmeticServer {	
	public static void main (String[] args) {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			RemoteMixedTypeProcessor mixedTypeProcessor = new AMixedTypeProcessor();
			//create proxy
			UnicastRemoteObject.exportObject(mixedTypeProcessor, 0);	
			// make it available for clients
			rmiRegistry.rebind(SERVER_NAME, mixedTypeProcessor);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
