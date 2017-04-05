package consensus;

public interface ConsensusVetoer<StateType> {

	boolean acceptProposal(float aProposalNumber, StateType aState);
	
}
