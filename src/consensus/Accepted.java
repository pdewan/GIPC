package consensus;

public interface Accepted<StateType> {
	void accepted(float aProposalNumber, StateType aProposal, boolean  anAgreement);
	public static final String ACCEPT_NOTIFICATION = "ACCEPTED";
	public static final String ACCEPT_AGREEMENT = "ACCEPT_AGREEMENT";


}
