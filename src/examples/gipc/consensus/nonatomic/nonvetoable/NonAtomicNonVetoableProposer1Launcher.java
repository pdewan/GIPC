package examples.gipc.consensus.nonatomic.nonvetoable;

import examples.gipc.consensus.sessionport.ExampleMemberLauncher;
import examples.gipc.consensus.sessionport.Member1;
import port.trace.consensus.ConsensusTraceUtility;

public class NonAtomicNonVetoableProposer1Launcher extends
		ANonAtomicNonVetoableProposerLauncher implements Member1 {

	public NonAtomicNonVetoableProposer1Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
		// TODO Auto-generated constructor stub
	}
	
	public void proposeValues() {
		proposeValues1();
	}

	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		new NonAtomicNonVetoableProposer1Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
