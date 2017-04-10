package examples.gipc.consensus.sessionport;

import consensus.ConsensusListener;
import consensus.ProposalState;

public class AGreetingConsensusListener implements ConsensusListener<String>{
	@Override
	public void newConsensusState(String aState) {
		System.out.println("Greeting:" + aState);		
	}

	@Override
	public void newLocalProposalState(float aProposalNumber, String aProposal,
			ProposalState aProposalState) {
		
		
	}

	@Override
	public void newRemoteProposalState(float aProposalNumber, String aProposal,
			ProposalState aProposalState) {
		
	}

	
	@Override
	public void newProposalState(float aProposalNumber, String aProposal,
			ProposalState aProposalState) {
		// TODO Auto-generated method stub
		
	}

}
