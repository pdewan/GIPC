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
	Float getMyLastProposalNumber();
	Float getLastProposalNumber();
	void removeConsensusListener(ConsensusListener<StateType> aConsensusListener);
	void addConsensusRejectioner(ProposalVetoer<StateType> aConsensusRejectioner);
	void removeConsensusRejectioner(ProposalVetoer<StateType> aConsensusRejectioner);
	boolean myLastProposalIsPending();
	Set<Float> getPendingProposals();
	public Float getLastConsensusProposal();
}
