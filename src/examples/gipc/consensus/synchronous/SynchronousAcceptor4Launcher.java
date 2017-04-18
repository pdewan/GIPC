package examples.gipc.consensus.synchronous;

import port.trace.buffer.BufferTraceUtility;
import port.trace.consensus.ConsensusTraceUtility;
import examples.gipc.consensus.AMeaningOfLifeVetoer;
import examples.gipc.consensus.Member4;

public class SynchronousAcceptor4Launcher 
//	extends AnAsymmetricMultiPartyLearnerLauncher 
extends ASynchronousMemberLauncher

	implements Member4{
	

	
	
public SynchronousAcceptor4Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

@Override
protected void addListenersAndVetoersToLocalMeaningOfLifeMechanism() {
	super.addListenersAndVetoersToLocalMeaningOfLifeMechanism();
	meaningOfLifeMechanism.addConsensusVetoer(new AMeaningOfLifeVetoer());
	
}

public static void main(String[] args) {
//	BufferTraceUtility.setTracing();

	ConsensusTraceUtility.setTracing();
	new SynchronousAcceptor4Launcher(MY_NAME, MY_PORT_NUMBER);
}


}
