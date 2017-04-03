package examples.gipc.counter.sessionport.twopartyconsensus.asymmetric;

import consensus.ConsensusListener;
import consensus.ProposalState;

public class AMeaningOfLifeConsensusListener implements ConsensusListener<Integer>{

	@Override
	public void newLocalProposalState(int aProposalNumber, Integer aProposal,
			ProposalState aProposalState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newRemoteProposalState(int aProposalNumber, Integer aProposal,
			ProposalState aProposalState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newConsensusState(Integer aState) {
		System.out.println("Meaning of Life:" + aState);		
	}

}