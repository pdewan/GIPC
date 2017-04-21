package consensus;

import java.util.Set;

import inputport.ConnectionListener;


public interface LocalConsensusMechanism<StateType> extends ConsensusState<StateType>, ConnectionListener  {
	float propose(StateType aProposal);	
	ProposalState waitForConsensus(float aProposalNumber);
	void addConsensusListener(ConsensusListener<StateType> aConsensusListener);
	boolean isPending(float aProposalNumber);
	boolean someProposalIsPending();
	ProposalState getProposalState(float aProposalNumber);
	Float myLastProposalNumber();
	Float lastProposalNumber();
	void removeConsensusListener(ConsensusListener<StateType> aConsensusListener);
	void addConsensusVetoer(ProposalVetoer<StateType> aConsensusVetoer);
	void removeConsensusVetoer(ProposalVetoer<StateType> aVetoer);
	boolean myLastProposalIsPending();
	Set<Float> pendingProposals();
	 Float getLastConsensusProposal();
	Set otherPendingProposals(float aProposalNumber);
	ProposalState waitForConsensus(float aProposalNumber, Long aWaitTime);


}
