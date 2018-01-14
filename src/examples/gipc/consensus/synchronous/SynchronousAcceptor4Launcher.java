package examples.gipc.consensus.synchronous;

import util.trace.port.buffer.BufferTraceUtility;
import util.trace.port.consensus.ConsensusTraceUtility;
import examples.gipc.consensus.AMeaningOfLifeVetoer;
import examples.gipc.consensus.Member4;

public class SynchronousAcceptor4Launcher 
extends ASynchronousMemberLauncher

	implements Member4{
	

	
	
public SynchronousAcceptor4Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

@Override
protected void addListenersAndVetoersToConsensusMechanisms() {
	super.addListenersAndVetoersToConsensusMechanisms();
	meaningOfLifeMechanism.addConsensusVetoer(new AMeaningOfLifeVetoer());	
}

public static void main(String[] args) {
	ConsensusTraceUtility.setTracing();
	new SynchronousAcceptor4Launcher(MY_NAME, MY_PORT_NUMBER);
}


}
