package inputport.datacomm;

public interface ReceiveNotifier<MessageType> {
	void notifyPortReceive(String aRemoteEnd, MessageType aMessage);

}
