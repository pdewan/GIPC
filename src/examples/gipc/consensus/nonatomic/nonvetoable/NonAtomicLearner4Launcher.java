package examples.gipc.consensus.nonatomic.nonvetoable;

import port.trace.consensus.ConsensusTraceUtility;
import examples.gipc.consensus.Member4;

public class NonAtomicLearner4Launcher 
//	extends AnAsymmetricMultiPartyLearnerLauncher 
extends ANonAtomicNonVetoableProposerLauncher

	implements Member4{
	

	
	
public NonAtomicLearner4Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

public static void main(String[] args) {
	ConsensusTraceUtility.setTracing();
	new ANonAtomicLearnerLauncher(MY_NAME, MY_PORT_NUMBER);
}


}
