package examples.mvc.rmi.duplex;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import examples.mvc.local.simplex.ProgramLauncher;

public class ADelegatingDistributedRMIServerMVC_Launcher extends  ADistributedRMIServerMVC_Launcher{
	
	protected Remote createUpperCaser() {
		try {
		return new ADistributedDelegatingRMICounter();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		

	}
	public static void main (String[] args) {
		(new ADelegatingDistributedRMIServerMVC_Launcher()).launch();

	}
}
