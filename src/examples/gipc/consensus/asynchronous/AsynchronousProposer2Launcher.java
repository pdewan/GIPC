package examples.gipc.consensus.asynchronous;

import examples.gipc.consensus.Member1;
import examples.gipc.consensus.Member2;
import port.trace.consensus.ConsensusTraceUtility;

public class AsynchronousProposer2Launcher extends
		AnAsynchronousProposerLauncher implements Member2 {

	public AsynchronousProposer2Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

	public void proposeValues() {
		proposeValues2();
	}

	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		new AsynchronousProposer2Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
