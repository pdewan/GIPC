package examples.gipc.consensus.nonatomic.nonvetoable;

import examples.gipc.consensus.sessionport.Member1;
import examples.gipc.consensus.sessionport.Member2;
import port.trace.consensus.ConsensusTraceUtility;

public class NonAtomicNonVetoableProposer2Launcher extends
		ANonAtomicNonVetoableProposerLauncher implements Member2 {

	public NonAtomicNonVetoableProposer2Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

	public void proposeValues() {
		proposeValues2();
	}

	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		new NonAtomicNonVetoableProposer2Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
