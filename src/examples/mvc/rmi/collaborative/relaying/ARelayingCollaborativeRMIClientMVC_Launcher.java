package examples.mvc.rmi.collaborative.relaying;

import java.rmi.server.UnicastRemoteObject;

import examples.mvc.local.duplex.DuplexFrostyModel;
import examples.mvc.local.simplex.ProgramLauncher;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.mvc.rmi.muser.AMultiUserRMIClientMVC_Launcher;

public class ARelayingCollaborativeRMIClientMVC_Launcher extends
		AMultiUserRMIClientMVC_Launcher implements ProgramLauncher {
	public ARelayingCollaborativeRMIClientMVC_Launcher(String aName,
			String aHost) {
		super(aName, aHost);
	}
	protected Echoer getEchoer() {
		DistributedRMIEchoer echoer = new ADistributedRMIEchoer();
		try {
			UnicastRemoteObject.exportObject(echoer, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Echoer) echoer;
	}
	protected DuplexFrostyModel getFrostyModel() {
		try {
			RelayingCollaborativeRMIUpperCaser upperCasePrinter = (RelayingCollaborativeRMIUpperCaser) serverRMIRegistry
					.lookup(upperCaserName());
			return new ARelayingCollaborativeRMIFrostyModel(upperCasePrinter,
					(DistributedRMIEchoer) getEchoer(),
					(DistributedRMICounter) getCounter(), name);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	protected String upperCaserName() {
		return ARelayingCollaborativeRMIServerMVC_Launcher.UPPER_CASER_NAME;
	}
	public static void main(String[] args) {
		(new ARelayingCollaborativeRMIClientMVC_Launcher("Test Client",
				"localhost")).launch();
	}
}
