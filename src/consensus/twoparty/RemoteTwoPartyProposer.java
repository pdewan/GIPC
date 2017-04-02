package consensus.twoparty;

public interface RemoteTwoPartyProposer<StateType> {
	void accepted(int aProposalNumber, StateType aProposal);
//	void rejected(int aProposalNumber, StateType aProposal);


}
