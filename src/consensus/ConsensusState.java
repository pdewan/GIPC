package consensus;

public interface ConsensusState<StateType> {
	StateType getConsensusState();
	StateType getStrongConsensusState();
//	void setConsensusState(StateType newVal);

}
