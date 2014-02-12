package examples.mvc.rmi.collaborative;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class BobCollaborativeRMIUpperCaseLauncher {
	public static void main (String[] args) {
		new ACollaborativeRMIClientMVCLauncher("Bob", "localhost").launch();
	}

}
