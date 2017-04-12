package examples.gipc.consensus.nonatomic;

import examples.gipc.consensus.ExampleMemberLauncher;
import examples.gipc.consensus.Member1;
import port.trace.consensus.ConsensusTraceUtility;

public class NonAtomicProposer1Launcher extends
		ANonAtomicProposerAndAcceptorLauncher implements Member1 {

	public NonAtomicProposer1Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
		// TODO Auto-generated constructor stub
	}
	
	public void proposeValues() {
		proposeValues1();
	}

	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		new NonAtomicProposer1Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
