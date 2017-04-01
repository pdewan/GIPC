package consensus.twoparty;

public interface RemoteTwoPartyProposer<StateType> {
//	void accepted(int aProposalNumber, StateType aProposal);
	void accepted(int aProposalNumber, StateType aProposal, boolean accepted);


}
