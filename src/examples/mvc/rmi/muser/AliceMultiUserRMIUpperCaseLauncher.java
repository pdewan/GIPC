package examples.mvc.rmi.muser;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class AliceMultiUserRMIUpperCaseLauncher {
	public static void main (String[] args) {
		new AMultiUserRMIClientMVC_Launcher("Alice", "localhost").launch();
	}
}
