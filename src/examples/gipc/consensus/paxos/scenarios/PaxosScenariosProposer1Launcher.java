package examples.gipc.consensus.paxos.scenarios;

import examples.gipc.consensus.Member1;
import util.trace.port.consensus.ConsensusTraceUtility;

public class PaxosScenariosProposer1Launcher extends
		APaxosScenariosMemberLauncher implements Member1 {

	public PaxosScenariosProposer1Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}
	
	
	protected void doPropose() {
//		proposeGreeting("Hello");
		proposeValues1();
	}

	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();

		new PaxosScenariosProposer1Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
