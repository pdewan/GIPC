package examples.gipc.counter.sessionport.twopartyconsensus;

import consensus.ConsensusListener;
import consensus.ProposalState;

public class AGreetingConsensusListener implements ConsensusListener<String>{

	@Override
	public void newLocalProposalState(int aProposalNumber, String aProposal,
			ProposalState aProposalState) {
		
		
	}

	@Override
	public void newRemoteProposalState(int aProposalNumber, String aProposal,
			ProposalState aProposalState) {
		
	}

	@Override
	public void newConsensusState(String aState) {
		System.out.println("Greeting:" + aState);		
	}

}
