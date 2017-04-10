package examples.gipc.consensus.nonatomic;

import examples.gipc.consensus.sessionport.Member1;
import examples.gipc.consensus.sessionport.Member2;
import port.trace.consensus.ConsensusTraceUtility;

public class NonAtomicProposer2Launcher extends
	ANonAtomicProposerAndAcceptorLauncher implements Member2 {

	public NonAtomicProposer2Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

	public void proposeValues() {
		proposeValues2();
	}

	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		new NonAtomicProposer2Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
