package examples.gipc.consensus.synchronous;

import port.trace.consensus.ConsensusTraceUtility;
import examples.gipc.consensus.AGreetingVetoer;
import examples.gipc.consensus.Member3;

public class SynchronousAcceptor3Launcher extends
//		AnAsymmetricMultiPartyLearnerLauncher 
ASynchronousMemberLauncher
		implements Member3 {

	public SynchronousAcceptor3Launcher(String aLocalName,
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
		new SynchronousAcceptor3Launcher(MY_NAME, MY_PORT_NUMBER);
	}

}
