package examples.gipc.consensus.centralizable;

import port.trace.consensus.ConsensusTraceUtility;
import examples.gipc.consensus.AMeaningOfLifeVetoer;
import examples.gipc.consensus.Member4;

public class CentralizableAcceptor4Launcher 
//	extends AnAsymmetricMultiPartyLearnerLauncher 
extends ACentralizableMemberLauncher

	implements Member4{
	

	
	
public CentralizableAcceptor4Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

@Override
protected void addListenersAndVetoersToLocalMeaningOfLifeMechanism() {
	super.addListenersAndVetoersToLocalMeaningOfLifeMechanism();
	meaningOfLifeMechanism.addConsensusVetoer(new AMeaningOfLifeVetoer());
	
}

public static void main(String[] args) {
	ConsensusTraceUtility.setTracing();
	new CentralizableAcceptor4Launcher(MY_NAME, MY_PORT_NUMBER);
}


}
