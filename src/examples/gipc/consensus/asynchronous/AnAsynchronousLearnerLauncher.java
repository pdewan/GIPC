package examples.gipc.consensus.asynchronous;

import consensus.ConsensusMechanism;
import consensus.ConsensusMechanismFactory;
import consensus.asynchronous.sequential.ALearnerConsensusMechanism;
import consensus.asynchronous.sequential.ALearnerConsensusMechanismFactory;
import consensus.asynchronous.sequential.AnAsynchronousConsensusMechanismFactory;
import consensus.asynchronous.sequential.Learned;
import examples.gipc.consensus.AnExampleConsensusMemberLauncher;
import examples.gipc.consensus.AnExampleGreetingMeaningConsensusMemberLauncher;

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
