package examples.gipc.consensus.asynchronous;

import trace.port.consensus.ConsensusTraceUtility;
import examples.gipc.consensus.Member3;

public class AsynchronousLearner3Launcher extends
//		AnAsymmetricMultiPartyLearnerLauncher 
		AnAsynchronousProposerLauncher
		implements Member3 {

	public AsynchronousLearner3Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		new AnAsynchronousLearnerLauncher(MY_NAME, MY_PORT_NUMBER);
	}

}
