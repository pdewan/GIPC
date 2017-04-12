package consensus;

public interface Learner<StateType> {
	void learn(float aProposalNumber, StateType aProposal, ProposalRejectionKind anAgreement);


}
