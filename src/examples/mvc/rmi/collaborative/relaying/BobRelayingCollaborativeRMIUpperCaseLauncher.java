package examples.mvc.rmi.collaborative.relaying;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class BobRelayingCollaborativeRMIUpperCaseLauncher {
	public static void main (String[] args) {
		new ARelayingCollaborativeRMIClientMVC_Launcher("Bob", "localhost").launch();
	}

}
