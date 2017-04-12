package examples.gipc.consensus;

import consensus.ConsensusListener;
import consensus.ProposalState;

public class AMeaningOfLifeConsensusListener implements ConsensusListener<Integer>{

	@Override
	public void newLocalProposalState(float aProposalNumber, Integer aProposal,
			ProposalState aProposalState) {
		
		
	}

	@Override
	public void newRemoteProposalState(float aProposalNumber, Integer aProposal,
			ProposalState aProposalState) {
		
	}

	@Override
	public void newConsensusState(Integer aState) {
		System.out.println("Meaning of Life:" + aState);		
	}

	@Override
	public void newProposalState(float aProposalNumber, Integer aProposal,
			ProposalState aProposalState) {
	
	}

}