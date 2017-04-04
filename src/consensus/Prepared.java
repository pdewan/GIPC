package consensus;

public interface Prepared<StateType> {
	void prepared(float aProposalNumber, StateType aProposal);


}
