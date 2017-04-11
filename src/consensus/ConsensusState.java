package consensus;

public interface ConsensusState<StateType> {
	StateType getLastConsensusState();
	StateType getConsensusState();
}
