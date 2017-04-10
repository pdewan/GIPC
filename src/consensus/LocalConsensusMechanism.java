package consensus;

import java.util.Set;

import inputport.ConnectionListener;


public interface LocalConsensusMechanism<StateType> extends ConsensusState<StateType>, ConnectionListener  {
	float propose(StateType aProposal);	
	ProposalState waitForStateChange(float aProposalNumber);
	ProposalState getProposalState(float aProposalNumber);
	Float getMyLastProposalNumber();
	Float getLastProposalNumber();
	void addConsensusListener(ConsensusListener<StateType> aConsensusListener);
	void removeConsensusListener(ConsensusListener<StateType> aConsensusListener);
	void addConsensusVetoer(ProposalVetoer<StateType> aConsensusVetoer);
	void removeConsensusVetoer(ProposalVetoer<StateType> aConsensusVetoer);
	boolean myLastProposalIsPending();
	boolean someProposalIsPending();
	boolean isPending(float aProposalNumber);
	Set<Float> getPendingProposals();
	public Float getLastConsensusProposal();
}
