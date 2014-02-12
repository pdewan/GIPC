package inputport.datacomm.group;




public interface GroupServerInputPortFactory<MessageType> {
	public GroupServerInputPort<MessageType> createGroupServerInputPort(String theServerId, String theServerName);
	
}
