package consensus;

public interface Preparer<StateType> {
	void prepare(float aProposalNumber, StateType aProposal);


}
