package examples.mvc.rmi.collaborative;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class AliceCollaborativeRMIUpperCaseLauncher {
	public static void main (String[] args) {
//		RMIUpperCaseClientLauncher.launch("Alice");
		new ACollaborativeRMIClientMVCLauncher("Alice", "localhost").launch();
	}

}
