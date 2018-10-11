package examples.gipc.consensus.paxos.scenarios;

import examples.gipc.consensus.Member2;
import util.trace.port.consensus.ConsensusTraceUtility;

public class PaxosScenariosAcceptor2Launcher extends
APaxosScenariosMemberLauncher
		implements Member2 {

	public PaxosScenariosAcceptor2Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}


	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		
		new PaxosScenariosAcceptor2Launcher(MY_NAME, MY_PORT_NUMBER);
	}

}
