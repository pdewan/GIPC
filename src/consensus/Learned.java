package consensus;

public interface Learned<StateType> {
	void learned(float aProposalNumber, StateType aProposal);


}
