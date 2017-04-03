package consensus.twoparty.symmetric;

public interface RemoteTwoPartyAcceptor<StateType> {
	void accept(int aProposalNumber, StateType aProposal);
}
