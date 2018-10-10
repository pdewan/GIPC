package consensus.synchronous.sequential;

import consensus.ProposalFeedbackKind;

public interface Accepted<StateType> extends Acceptor<StateType>{
	void accepted(float aProposalNumber, StateType aProposal, ProposalFeedbackKind  aRejectionKind);
	public static final String ACCEPT_NOTIFICATION = "ACCEPTED";
	public static final String ACCEPT_SUCCESS = "ACCEPT_SUCCESS";


}
