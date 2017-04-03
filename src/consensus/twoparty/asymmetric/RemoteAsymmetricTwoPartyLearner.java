package consensus.twoparty.asymmetric;

public interface RemoteAsymmetricTwoPartyLearner<StateType> {
	Object learn(int aProposalNumber, StateType aProposal);


}
