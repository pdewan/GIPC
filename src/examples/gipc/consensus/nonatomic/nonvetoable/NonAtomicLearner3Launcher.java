package examples.gipc.consensus.nonatomic.nonvetoable;

import port.trace.consensus.ConsensusTraceUtility;
import examples.gipc.consensus.Member3;

public class NonAtomicLearner3Launcher extends
//		AnAsymmetricMultiPartyLearnerLauncher 
		ANonAtomicNonVetoableProposerLauncher
		implements Member3 {

	public NonAtomicLearner3Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		new ANonAtomicLearnerLauncher(MY_NAME, MY_PORT_NUMBER);
	}

}
