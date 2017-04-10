package consensus;

import java.rmi.Remote;

public interface Prepared<StateType> extends Remote{
	void prepared(float aProposalNumber, StateType aProposal);
	public static final String PREPARED_NOTIFICATION = "PREPARED";
	public static final String PREPARED_AGREEMENT = "PREPARED_AGREEMENT";

}
