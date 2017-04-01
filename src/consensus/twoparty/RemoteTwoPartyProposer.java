package consensus.twoparty;

public interface RemoteTwoPartyProposer<StateType> {
	void accept(int aProposalNumber, StateType aProposal);

}
