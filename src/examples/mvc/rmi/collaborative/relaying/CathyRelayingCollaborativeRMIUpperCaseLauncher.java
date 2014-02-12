package examples.mvc.rmi.collaborative.relaying;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class CathyRelayingCollaborativeRMIUpperCaseLauncher {
	public static void main (String[] args) {
		new ARelayingCollaborativeRMIClientMVC_Launcher("Cathy", "localhost").launch();
	}

}
