package consensus.twoparty.symmetric;

public interface Acceptor<StateType> {
	void accept(float aProposalNumber, StateType aProposal);
}
