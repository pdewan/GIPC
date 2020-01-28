package examples.mvc.rmi.muser;

import examples.mvc.local.duplex.DuplexFrostyModel;
import examples.mvc.local.simplex.ProgramLauncher;
import examples.mvc.rmi.duplex.ADistributedRMIClientMVC_Launcher;
import examples.rmi.counter.DistributedCounter;

public class AMultiUserRMIClientMVC_Launcher extends
		ADistributedRMIClientMVC_Launcher implements ProgramLauncher {
	public AMultiUserRMIClientMVC_Launcher(String aName, String aHost) {
		super(aName, aHost);
	}
	protected String counterName() {
		return name + COUNTER_NAME;
	}
	protected String upperCaserName() {
		return AMultiUserRMIServerMVC_Launcher.UPPER_CASER_NAME;
	}
	protected DuplexFrostyModel getFrostyModel() {
		try {
			MultiUserRMIUpperCaser upperCasePrinter = (MultiUserRMIUpperCaser) serverRMIRegistry
					.lookup(upperCaserName());
			return new AMultiUserRMIFrostyModel(upperCasePrinter,
					(DistributedCounter) getCounter(), name);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) {
		(new AMultiUserRMIClientMVC_Launcher("Test Client", "localhost"))
				.launch();
	}

}
