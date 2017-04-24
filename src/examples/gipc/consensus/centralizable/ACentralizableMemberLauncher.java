package examples.gipc.consensus.centralizable;

import consensus.ConsensusMechanism;
import consensus.ConsensusMechanismFactory;
import consensus.ReplicationSynchrony;
import consensus.central.ACentralizableConsensusMechanismFactory;
import consensus.synchronous.sequential.ASynchronousConsensusMechanism;
import consensus.synchronous.sequential.ASynchronousConsensusMechanismFactory;
import consensus.synchronous.sequential.Accepted;
import consensus.synchronous.sequential.Acceptor;
import examples.gipc.consensus.AnExampleProposerLauncher;
import examples.gipc.consensus.AnExampleGreetingMeaningConsensusProposerLauncher;

public class ACentralizableMemberLauncher extends AnExampleProposerLauncher  {

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
