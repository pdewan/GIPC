package consensus.twoparty;

public interface RemoteTwoPartyAcceptor<StateType> {
	void accepted(int aProposalNumber, StateType aProposal, boolean accepted);
}
