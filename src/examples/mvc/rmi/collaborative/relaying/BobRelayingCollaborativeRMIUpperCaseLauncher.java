package examples.mvc.rmi.collaborative.relaying;




public class BobRelayingCollaborativeRMIUpperCaseLauncher {
	public static void main (String[] args) {
		new ARelayingCollaborativeRMIClientMVC_Launcher("Bob", "localhost").launch();
	}

}
