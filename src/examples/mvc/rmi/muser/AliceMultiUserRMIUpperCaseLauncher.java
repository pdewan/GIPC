package examples.mvc.rmi.muser;




public class AliceMultiUserRMIUpperCaseLauncher {
	public static void main (String[] args) {
		new AMultiUserRMIClientMVC_Launcher("Alice", "localhost").launch();
	}
}
