package consensus.twoparty;

public interface RemoteTwoPartyAcceptor<StateType> {
	void accept(int aProposalNumber, StateType aProposal);
}
