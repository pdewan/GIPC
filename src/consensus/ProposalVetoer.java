package consensus;

public interface ProposalVetoer<StateType> {

	ProposalVetoKind acceptProposal(float aProposalNumber, StateType aState);
	
}
