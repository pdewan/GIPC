package inputport.datacomm.duplex;



public interface DuplexClientInputPortFactory<MessageType> {
	DuplexClientInputPort<MessageType> createDuplexClientInputPort(String theHost, String theServerId, String aServerName, String theClientName);
}
