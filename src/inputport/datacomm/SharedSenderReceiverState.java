package inputport.datacomm;

public interface SharedSenderReceiverState<StateType> {
	public StateType getSharedSenderReceiverState();	
	public void setSharedSenderReceiverState(StateType newVal);
}
