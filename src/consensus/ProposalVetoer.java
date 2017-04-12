package consensus;

public interface ProposalVetoer<StateType> {

	ProposalRejectionKind acceptProposal(float aProposalNumber, StateType aState);
	
}
