package inputport.datacomm;



public interface ImplicitSender<MessageType> {
	void send (MessageType message);

}
