package consensus;

public interface Acceptor<StateType> {
	void accept(float aProposalNumber, StateType aProposal);


}
