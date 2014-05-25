package examples.mvc.rmi.muser;




public class BobMultiUserRMIUpperCaseLauncher {
	public static void main (String[] args) {
		new AMultiUserRMIClientMVC_Launcher("Bob", "localhost").launch();
	}

}
