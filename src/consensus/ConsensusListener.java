package consensus;

public interface ConsensusListener<StateType> {
	void newLocalProposalState(float aProposalNumber, StateType aProposal, ProposalState aProposalState);
	void newRemoteProposalState(float aProposalNumber, StateType aProposal, ProposalState aProposalState);
	void newProposalState(float aProposalNumber, StateType aProposal, ProposalState aProposalState);
	void newConsensusState(StateType aState);
}
