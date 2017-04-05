package consensus;

public interface Prepared<StateType> {
	void prepared(float aProposalNumber, StateType aProposal);
	public static final String PREPARED_NOTIFICATION = "PREPARED";
	public static final String PREPARED_AGREEMENT = "PREPARED_AGREEMENT";

}
