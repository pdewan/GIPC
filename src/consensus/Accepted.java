package consensus;

import java.rmi.Remote;

public interface Accepted<StateType> extends Acceptor<StateType>{
	void accepted(float aProposalNumber, StateType aProposal, ProposalVetoKind  aVetoKind);
	public static final String ACCEPT_NOTIFICATION = "ACCEPTED";
	public static final String ACCEPT_SUCCESS = "ACCEPT_SUCCESS";


}
