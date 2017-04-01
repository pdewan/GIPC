package consensus;

public interface ConsensusListener<StateType> {
//	void localProposalAccepted(int aProposalNumber, StateType aProposal, boolean accepted);
//	void remoteProposalAccepted(int aProposalNumber, StateType aProposal, boolean accepted);
//	void proposalCommunicationError(int aProposalNumber, StateType aProposal);
	void myLastProposalState(StateType aProposal, ProposalState aProposalState);
	void newProposalState(int aProposalNumber, StateType aProposal, ProposalState aProposalState);
	void newConsensusState(StateType aState);
}
