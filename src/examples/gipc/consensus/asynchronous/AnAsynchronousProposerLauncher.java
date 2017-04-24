package examples.gipc.consensus.asynchronous;

import consensus.ConsensusMechanismFactory;
import consensus.asynchronous.sequential.AnAsynchronousConsensusMechanismFactory;
import examples.gipc.consensus.AnExampleGreetingMeaningConsensusProposerLauncher;
import examples.gipc.consensus.AnExampleProposerLauncher;

public class AnAsynchronousProposerLauncher extends AnExampleProposerLauncher  {

	public AnAsynchronousProposerLauncher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}
	@Override
	protected ConsensusMechanismFactory<Integer> meaningConsensusMechanismFactory() {
		return new AnAsynchronousConsensusMechanismFactory<>();
	}
	@Override
	protected void customizeConsensusMechanisms() {
		
	}
	
}
