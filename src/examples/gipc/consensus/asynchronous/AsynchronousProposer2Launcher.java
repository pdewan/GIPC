package examples.gipc.consensus.asynchronous;

import examples.gipc.consensus.Member1;
import examples.gipc.consensus.Member2;
import util.trace.port.consensus.ConsensusTraceUtility;

public class AsynchronousProposer2Launcher extends
		AnAsynchronousProposerLauncher implements Member2 {

	public AsynchronousProposer2Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

	protected void doPropose() {
		proposeValues2();
	}

	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		new AsynchronousProposer2Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
