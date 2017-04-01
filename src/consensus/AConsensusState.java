package consensus;

public class AConsensusState<StateType> implements ConsensusState<StateType> {
	StateType state;
	@Override
	public StateType getConsensusState() {
		return state;
	}
	@Override
	public void setConsensusState(StateType newVal) {
		state = newVal;		
	}
}
