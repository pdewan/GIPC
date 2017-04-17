package consensus.asynchronous;

import consensus.ProposalFeedbackKind;

public interface Learner<StateType> {
	void learn(float aProposalNumber, StateType aProposal, ProposalFeedbackKind anAgreement);


}
