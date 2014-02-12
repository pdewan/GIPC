package inputport.datacomm;


public interface TrappableReceiver<MessageType>  {
	ReceiveNotifier<MessageType> getReceiveTrapper();
	void setReceiveTrapper(ReceiveNotifier<MessageType> newVal);
}
