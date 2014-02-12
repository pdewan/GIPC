package examples.mvc.rmi.duplex;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import examples.mvc.local.simplex.ProgramLauncher;

public class ADistributedRMIServerMVC_Launcher implements ProgramLauncher{
	public static final String UPPER_CASER_NAME = DistributedRMIUpperCaser.class.getName();
	public ADistributedRMIServerMVC_Launcher() {}
	protected String upperCaserName() {
		return UPPER_CASER_NAME;
	}
	
	public void launch() {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			Remote upperCaser = createUpperCaser();
			UnicastRemoteObject.exportObject(upperCaser, 0);
			rmiRegistry.rebind(upperCaserName(), upperCaser);
		} catch (RemoteException e) {
			e.printStackTrace();
		}		
	}	
	protected Remote createUpperCaser() {
		return new ADistributedRMIUpperCaser();

	}
	public static void main (String[] args) {
		(new ADistributedRMIServerMVC_Launcher()).launch();

	}
}
