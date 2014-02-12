package inputport.datacomm;

public interface TrappableSender<MessageType>  {
	NamingSender<MessageType> getSendTrapper();
	void setSendTrapper(NamingSender<MessageType> newVal);

}
