package examples.gipc.consensus.nonatomic;

import port.trace.consensus.ConsensusTraceUtility;
import examples.gipc.consensus.AGreetingVetoer;
import examples.gipc.consensus.Member3;

public class NonAtomicAcceptor3Launcher extends
//		AnAsymmetricMultiPartyLearnerLauncher 
ANonAtomicProposerAndAcceptorLauncher
		implements Member3 {

	public NonAtomicAcceptor3Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}
	@Override
	protected void addListenersAndVetoersToLocalGreetingMechanism() {
		super.addListenersAndVetoersToLocalGreetingMechanism();
		greetingMechanism.addConsensusVetoer(new AGreetingVetoer());
		
	}

	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		new NonAtomicAcceptor3Launcher(MY_NAME, MY_PORT_NUMBER);
	}

}
