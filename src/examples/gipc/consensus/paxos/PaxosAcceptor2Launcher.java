package examples.gipc.consensus.paxos;

import port.trace.consensus.ConsensusTraceUtility;
import examples.gipc.consensus.AGreetingVetoer;
import examples.gipc.consensus.Member2;
import examples.gipc.consensus.Member3;

public class PaxosAcceptor2Launcher extends
APaxosMemberLauncher
		implements Member2 {

	public PaxosAcceptor2Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}


	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		
		new PaxosAcceptor2Launcher(MY_NAME, MY_PORT_NUMBER);
	}

}
