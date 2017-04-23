package examples.gipc.consensus.asynchronous;

import consensus.ConsensusMechanism;
import consensus.ConsensusMechanismFactory;
import consensus.asynchronous.ALearnerConsensusMechanism;
import consensus.asynchronous.ALearnerConsensusMechanismFactory;
import consensus.asynchronous.AnAsynchronousConsensusMechanismFactory;
import consensus.asynchronous.Learned;
import examples.gipc.consensus.AMeaningConsensusMemberLauncher;
import examples.gipc.consensus.AnExampleGreetingMeaningConsensusMemberLauncher;

public class AnAsynchronousLearnerLauncher extends
		AMeaningConsensusMemberLauncher {
	
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
