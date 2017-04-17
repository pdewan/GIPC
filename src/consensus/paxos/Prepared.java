package consensus.paxos;

import consensus.ProposalFeedbackKind;
import consensus.central.ProposeServer;

public interface Prepared<StateType> extends Preparer<StateType>{
	void prepared(float aProposalNumber, StateType aProposal, float aPreparedProposalNumber, ProposalFeedbackKind aRejectionKind);
	public static final String PREPARE_NOTIFICATION = "PREPARED";
	public static final String PREPARE_SUCCESS = "PREPARE_SUCCESS";
}
