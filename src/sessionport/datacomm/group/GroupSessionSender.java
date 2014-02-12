package sessionport.datacomm.group;

public interface GroupSessionSender<MessageType> {
	public void sendOtherClients(MessageType message);
	public void sendOtherServers(MessageType message);
	public void sendOtherMembers(MessageType message);
	
	public void sendAllClients(MessageType message);
	public void sendAllServers(MessageType message);
	public void sendAllMembers(MessageType message);

}
