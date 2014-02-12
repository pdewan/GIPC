package examples.mvc.rmi.collaborative.relaying;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class AliceRelayingCollaborativeRMIUpperCaseLauncher {
	public static void main (String[] args) {
//		RMIUpperCaseClientLauncher.launch("Alice");
		new ARelayingCollaborativeRMIClientMVC_Launcher("Alice", "localhost").launch();
	}

}
