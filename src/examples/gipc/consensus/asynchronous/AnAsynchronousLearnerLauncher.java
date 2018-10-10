package examples.gipc.consensus.asynchronous;

import consensus.ConsensusMechanismFactory;
import consensus.asynchronous.sequential.ALearnerConsensusMechanismFactory;
import examples.gipc.consensus.AnExampleConsensusMemberLauncher;

public class AnAsynchronousLearnerLauncher extends
		AnExampleConsensusMemberLauncher {
	
	public AnAsynchronousLearnerLauncher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}


	@Override
	protected ConsensusMechanismFactory<Integer> meaningConsensusMechanismFactory() {
		return new ALearnerConsensusMechanismFactory<>();
	}


	@Override
	protected void customizeConsensusMechanisms() {		
	}

	



}
