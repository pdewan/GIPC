package examples.mvc.rmi.collaborative;




public class BobCollaborativeRMIUpperCaseLauncher {
	public static void main (String[] args) {
		new ACollaborativeRMIClientMVCLauncher("Bob", "localhost").launch();
	}

}
