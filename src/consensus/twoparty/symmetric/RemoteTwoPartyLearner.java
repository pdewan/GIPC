package consensus.twoparty.symmetric;

public interface RemoteTwoPartyLearner<StateType> {
	void learn(int aProposalNumber, StateType aProposal);


}
