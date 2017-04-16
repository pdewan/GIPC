package consensus.asynchronous;

import consensus.ProposalRejectionKind;

public interface Learner<StateType> {
	void learn(float aProposalNumber, StateType aProposal, ProposalRejectionKind anAgreement);


}
