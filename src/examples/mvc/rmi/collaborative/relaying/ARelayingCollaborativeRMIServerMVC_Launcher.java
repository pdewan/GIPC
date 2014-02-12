package examples.mvc.rmi.collaborative.relaying;


import java.rmi.Remote;

import examples.mvc.local.simplex.ProgramLauncher;
import examples.mvc.rmi.collaborative.ACollaborativeRMIServerMVCLauncher;
import examples.mvc.rmi.collaborative.ACollaborativeRMIUpperCaser;

public class ARelayingCollaborativeRMIServerMVC_Launcher extends ACollaborativeRMIServerMVCLauncher implements ProgramLauncher{
	@Override
	protected Remote createUpperCaser() {
		return new ARelayingCollaborativeRMIUpperCaser();
	}
	public static void main (String[] args) {
		(new ARelayingCollaborativeRMIServerMVC_Launcher()).launch();
	}
}
