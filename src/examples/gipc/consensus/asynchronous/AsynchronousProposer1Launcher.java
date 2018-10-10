package examples.gipc.consensus.asynchronous;

import examples.gipc.consensus.Member1;
import util.trace.port.consensus.ConsensusTraceUtility;

public class AsynchronousProposer1Launcher extends
		AnAsynchronousProposerLauncher implements Member1 {

	public AsynchronousProposer1Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
		// TODO Auto-generated constructor stub
	}
	
	protected void doPropose() {
		proposeValues1();
	}

	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		new AsynchronousProposer1Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
