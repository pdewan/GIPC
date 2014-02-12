package examples.mvc.rmi.muser;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class BobMultiUserRMIUpperCaseLauncher {
	public static void main (String[] args) {
		new AMultiUserRMIClientMVC_Launcher("Bob", "localhost").launch();
	}

}
