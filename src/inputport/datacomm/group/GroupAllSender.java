package inputport.datacomm.group;

public interface GroupAllSender<MessageType> {
	public void sendAll(MessageType message);

}
