package examples.gipc.consensus.paxos;

import port.trace.consensus.ConsensusTraceUtility;
import examples.gipc.consensus.AMeaningOfLifeVetoer;
import examples.gipc.consensus.Member4;

public class PaxosAcceptor4Launcher 
//	extends AnAsymmetricMultiPartyLearnerLauncher 
extends APaxosMemberLauncher

	implements Member4{
	

	
	
public PaxosAcceptor4Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

@Override
protected void addListenersAndVetoersToLocalMeaningOfLifeMechanism() {
	super.addListenersAndVetoersToLocalMeaningOfLifeMechanism();
//	meaningOfLifeMechanism.addConsensusVetoers(new AMeaningOfLifeVetoer());
	
}

public static void main(String[] args) {
	ConsensusTraceUtility.setTracing();
	new PaxosAcceptor4Launcher(MY_NAME, MY_PORT_NUMBER);
}


}
