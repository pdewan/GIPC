package consensus;

import java.util.Set;

import inputport.ConnectionListener;

public interface LocalConsensusMechanism<StateType> extends
		ConsensusState<StateType>,
		ConnectionListener {
	/**
	 * propose a new value, and get back an ID for the proposal
	 */
	float propose(StateType aProposal);		
	/**
	 * Register a consensus listner, calld each time global consensus is reached
	 */
	

	void addConsensusListener(ConsensusListener<StateType> aConsensusListener);
	/**
	 * remove consensus listener
	 */
	void removeConsensusListener(ConsensusListener<StateType> aConsensusListener);
	/**
	 * A local or remote proposal is pending
	 */
	boolean someProposalIsPending();

	/**
	 * block program until consensus is reached on the argument
	 */
	ProposalState waitForConsensus(float aProposalNumber);

	/**
	 * return true if the last proposed value has not reached consensus
	 */
	boolean isPending(float aProposalNumber);


	ProposalState getProposalState(float aProposalNumber);

	Float myLastProposalNumber();

	Float lastProposalNumber();

	void addConsensusVetoer(ProposalVetoer<StateType> aConsensusVetoer);

	void removeConsensusVetoer(ProposalVetoer<StateType> aVetoer);

	boolean myLastProposalIsPending();

	Set<Float> pendingProposals();

	Float getLastConsensusProposal();

	Set otherPendingProposals(float aProposalNumber);

	ProposalState waitForConsensus(float aProposalNumber, Long aWaitTime);

}
