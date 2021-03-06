package examples.gipc.consensus.asynchronous;

import examples.gipc.consensus.Member4;
import util.trace.port.consensus.ConsensusTraceUtility;

public class AsynchronousLearner4Launcher 
//	extends AnAsymmetricMultiPartyLearnerLauncher 
extends AnAsynchronousProposerLauncher

	implements Member4{
	

	
	
public AsynchronousLearner4Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

public static void main(String[] args) {
	ConsensusTraceUtility.setTracing();
	new AnAsynchronousLearnerLauncher(MY_NAME, MY_PORT_NUMBER);
}


}
