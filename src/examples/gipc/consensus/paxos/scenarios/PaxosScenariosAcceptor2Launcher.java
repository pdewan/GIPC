package examples.gipc.consensus.paxos.scenarios;

import port.trace.consensus.ConsensusTraceUtility;
import examples.gipc.consensus.AGreetingVetoer;
import examples.gipc.consensus.Member2;
import examples.gipc.consensus.Member3;

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
