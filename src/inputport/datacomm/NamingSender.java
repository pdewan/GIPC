package inputport.datacomm;

public interface NamingSender<MessageType> {
	void send(String aRemoteEnd, MessageType aMessage);

}
