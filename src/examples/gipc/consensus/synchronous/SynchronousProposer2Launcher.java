package examples.gipc.consensus.synchronous;

import examples.gipc.consensus.Member1;
import examples.gipc.consensus.Member2;
import port.trace.consensus.ConsensusTraceUtility;

public class SynchronousProposer2Launcher extends
	ASynchronousProposerAndAcceptorLauncher implements Member2 {

	public SynchronousProposer2Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

	public void proposeValues() {
		proposeValues2();
	}

	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		new SynchronousProposer2Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
