package consensus;

public interface ConsensusListener<StateType> {
//	void localProposalAccepted(float aProposalNumber, StateType aProposal, boolean accepted);
//	void remoteProposalAccepted(float aProposalNumber, StateType aProposal, boolean accepted);
//	void proposalCommunicationError(float aProposalNumber, StateType aProposal);
	void newLocalProposalState(float aProposalNumber, StateType aProposal, ProposalState aProposalState);
	void newRemoteProposalState(float aProposalNumber, StateType aProposal, ProposalState aProposalState);
	void newConsensusState(StateType aState);
}
