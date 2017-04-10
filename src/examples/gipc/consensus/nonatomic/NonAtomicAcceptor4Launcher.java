package examples.gipc.consensus.nonatomic;

import port.trace.consensus.ConsensusTraceUtility;
import examples.gipc.consensus.sessionport.AMeaningOfLifeVetoer;
import examples.gipc.consensus.sessionport.Member4;

public class NonAtomicAcceptor4Launcher 
//	extends AnAsymmetricMultiPartyLearnerLauncher 
extends ANonAtomicProposerAndAcceptorLauncher

	implements Member4{
	

	
	
public NonAtomicAcceptor4Launcher(String aLocalName,
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
	new NonAtomicAcceptor4Launcher(MY_NAME, MY_PORT_NUMBER);
}


}
