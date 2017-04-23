package examples.gipc.consensus.centralizable;

import consensus.ConsensusMechanism;
import consensus.ConsensusMechanismFactory;
import consensus.ReplicationSynchrony;
import consensus.central.ACentralizableConsensusMechanismFactory;
import consensus.synchronous.ASynchronousConsensusMechanism;
import consensus.synchronous.ASynchronousConsensusMechanismFactory;
import consensus.synchronous.Accepted;
import consensus.synchronous.Acceptor;
import examples.gipc.consensus.AMeaningConsensusProposerLauncher;
import examples.gipc.consensus.AnExampleGreetingMeaningConsensusProposerLauncher;

public class ACentralizableMemberLauncher extends AMeaningConsensusProposerLauncher  {

	public ACentralizableMemberLauncher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

	@Override
	protected ConsensusMechanismFactory<Integer> meaningConsensusMechanismFactory() {
		return new ACentralizableConsensusMechanismFactory<>();
	}

	@Override
	protected void customizeConsensusMechanisms() {
		meaningOfLifeMechanism.setCentralized(true);
		meaningOfLifeMechanism.setAcceptSynchrony(ReplicationSynchrony.ALL_SYNCHRONOUS);
		
		
	}
	


}
