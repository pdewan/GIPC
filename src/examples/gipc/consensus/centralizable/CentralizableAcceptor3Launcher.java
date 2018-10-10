package examples.gipc.consensus.centralizable;

import examples.gipc.consensus.Member3;
import util.trace.port.consensus.ConsensusTraceUtility;

public class CentralizableAcceptor3Launcher extends
//		AnAsymmetricMultiPartyLearnerLauncher 
ACentralizableMemberLauncher
		implements Member3 {

	public CentralizableAcceptor3Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}
	
	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		new CentralizableAcceptor3Launcher(MY_NAME, MY_PORT_NUMBER);
	}

}
