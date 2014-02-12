package examples.mvc.rmi.collaborative;


import java.rmi.Remote;

import examples.mvc.local.simplex.ProgramLauncher;
import examples.mvc.rmi.muser.AMultiUserRMIServerMVC_Launcher;

public class ACollaborativeRMIServerMVCLauncher extends AMultiUserRMIServerMVC_Launcher implements ProgramLauncher{
	@Override
	protected Remote createUpperCaser() {
		return new ACollaborativeRMIUpperCaser();
	}
	public static void main (String[] args) {
		(new ACollaborativeRMIServerMVCLauncher()).launch();
	}
}
