package inputport.datacomm.duplex;



public interface DuplexServerInputPortFactory<MessageType> {
	public DuplexServerInputPort<MessageType> createDuplexServerInputPort(String theServerId, String theServerName);
	
}
