package examples.gipc.consensus.paxos;

import port.trace.consensus.ConsensusTraceUtility;
import examples.gipc.consensus.AGreetingVetoer;
import examples.gipc.consensus.Member3;

public class PaxosAcceptor3Launcher extends
//		AnAsymmetricMultiPartyLearnerLauncher 
APaxosMemberLauncher
		implements Member3 {

	public PaxosAcceptor3Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}
	@Override
	protected void addListenersAndVetoersToLocalGreetingMechanism() {
		super.addListenersAndVetoersToLocalGreetingMechanism();
//		greetingMechanism.addConsensusVetoers(new AGreetingVetoer());
		
	}

	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		new PaxosAcceptor3Launcher(MY_NAME, MY_PORT_NUMBER);
	}

}
