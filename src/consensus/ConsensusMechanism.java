package consensus;

public interface ConsensusMechanism<StateType> extends ConsensusState<StateType> {
	void propose(int aProposalNumber, StateType aProposal);	
	ConsensusResult waitForConsensus(int aProposalNumber);
	void addConsensusListener(ConsensusListener<StateType> aConsensusListener);
	void removeConsensusListener(ConsensusListener<StateType> aConsensusListener);

}
