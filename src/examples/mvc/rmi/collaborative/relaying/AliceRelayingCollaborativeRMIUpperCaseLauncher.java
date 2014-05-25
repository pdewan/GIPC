package examples.mvc.rmi.collaborative.relaying;




public class AliceRelayingCollaborativeRMIUpperCaseLauncher {
	public static void main (String[] args) {
//		RMIUpperCaseClientLauncher.launch("Alice");
		new ARelayingCollaborativeRMIClientMVC_Launcher("Alice", "localhost").launch();
	}

}
