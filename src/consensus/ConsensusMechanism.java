package consensus;

import java.util.Set;

import inputport.ConnectionListener;


public interface ConsensusMechanism<StateType> extends ConsensusState<StateType>  {
	int propose(StateType aProposal);	
	ProposalState waitForConsensus(int aProposalNumber);
	ProposalState getProposalState(int aProposalNumber);
	Integer getMyLastProposalNumber();
	Integer getLastProposalNumber();
	void addConsensusListener(ConsensusListener<StateType> aConsensusListener);
	void removeConsensusListener(ConsensusListener<StateType> aConsensusListener);
	boolean myLastProposalIsPending();
	Set<Integer> getMyPendingProposals();

}
