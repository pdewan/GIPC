package examples.mvc.rmi.muser;


import java.rmi.Remote;

import examples.mvc.local.simplex.ProgramLauncher;
import examples.mvc.rmi.duplex.ADistributedRMIServerMVC_Launcher;

public class AMultiUserRMIServerMVC_Launcher extends ADistributedRMIServerMVC_Launcher implements ProgramLauncher{
	public static final String UPPER_CASER_NAME = MultiUserRMIUpperCaser.class.getName();
	protected String upperCaserName() {
		return UPPER_CASER_NAME;
	}	
	protected Remote createUpperCaser() {
		return new AMultiUserRMIUpperCaser();
	}
	public static void main (String[] args) {
		(new AMultiUserRMIServerMVC_Launcher()).launch();
	}
}
