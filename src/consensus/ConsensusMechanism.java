package consensus;

import java.util.Set;

import inputport.ConnectionListener;


public interface ConsensusMechanism<StateType> extends ConsensusState<StateType>, ConnectionListener  {
	float propose(StateType aProposal);	
	ProposalState waitForStateChange(float aProposalNumber);
	ProposalState getProposalState(float aProposalNumber);
	Float getMyLastProposalNumber();
	Float getLastProposalNumber();
	void addConsensusListener(ConsensusListener<StateType> aConsensusListener);
	void removeConsensusListener(ConsensusListener<StateType> aConsensusListener);
	boolean myLastProposalIsPending();
	boolean someProposalIsPending();
	boolean isPending(float aProposalNumber);
	Set<Float> getPendingProposals();

}
