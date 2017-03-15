package inputport.datacomm.duplex.object.explicitreceive;


public interface DuplexInputPortWithSyncReceiveFactory<MessageType>  {
	DuplexClientInputPortWithSyncReceive<MessageType> createDuplexClientInputPortWithSyncReceive(String theHost, String theServerId, String aServerName, String theClientName);
	DuplexServerInputPortWithSyncReceive<MessageType> createDuplexServerInputPortWithSyncReceive(String aServerId, String aServerName);

}
