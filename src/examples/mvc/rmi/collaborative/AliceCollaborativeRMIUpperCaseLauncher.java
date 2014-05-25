package examples.mvc.rmi.collaborative;




public class AliceCollaborativeRMIUpperCaseLauncher {
	public static void main (String[] args) {
//		RMIUpperCaseClientLauncher.launch("Alice");
		new ACollaborativeRMIClientMVCLauncher("Alice", "localhost").launch();
	}

}
