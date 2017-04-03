package examples.gipc.counter.sessionport.twopartyconsensus.symmetric;

import consensus.ConsensusListener;
import consensus.ProposalState;

public class AGreetingConsensusListener implements ConsensusListener<String>{

	@Override
	public void newLocalProposalState(float aProposalNumber, String aProposal,
			ProposalState aProposalState) {
		
		
	}

	@Override
	public void newRemoteProposalState(float aProposalNumber, String aProposal,
			ProposalState aProposalState) {
		
	}

	@Override
	public void newConsensusState(String aState) {
		System.out.println("Greeting:" + aState);		
	}

}
