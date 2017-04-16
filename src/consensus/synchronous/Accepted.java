package consensus.synchronous;

import java.rmi.Remote;

import consensus.ProposalRejectionKind;

public interface Accepted<StateType> extends Acceptor<StateType>{
	void accepted(float aProposalNumber, StateType aProposal, ProposalRejectionKind  aRejectionKind);
	public static final String ACCEPT_NOTIFICATION = "ACCEPTED";
	public static final String ACCEPT_SUCCESS = "ACCEPT_SUCCESS";


}
