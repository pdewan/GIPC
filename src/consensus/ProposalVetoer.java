package consensus;

public interface ProposalVetoer<StateType> {

	ProposalFeedbackKind acceptProposal(float aProposalNumber, StateType aState);
	
}
