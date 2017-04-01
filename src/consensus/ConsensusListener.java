package consensus;

public interface ConsensusListener<StateType> {
	void localProposalAccepted(int aProposalNumber, StateType aProposal, boolean accepted);
	void remoteProposalAccepted(int aProposalNumber, StateType aProposal, boolean accepted);
	void proposalCommunicationError(int aProposalNumber, StateType aProposal);
	
}
