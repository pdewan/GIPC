package consensus.paxos;

import consensus.ProposalFeedbackKind;

public interface Prepared<StateType> extends Preparer<StateType>{
	void prepared(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aRejectionKind);
	public static final String PREPARE_NOTIFICATION = "PREPARED";
	public static final String PREPARE_SUCCESS = "PREPARE_SUCCESS";
}
