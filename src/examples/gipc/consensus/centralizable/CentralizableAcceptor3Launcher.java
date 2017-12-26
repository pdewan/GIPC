package examples.gipc.consensus.centralizable;

import trace.port.consensus.ConsensusTraceUtility;
import examples.gipc.consensus.AGreetingVetoer;
import examples.gipc.consensus.Member3;

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
