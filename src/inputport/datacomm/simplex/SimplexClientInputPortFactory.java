package inputport.datacomm.simplex;



public interface SimplexClientInputPortFactory<MessageType> {
	SimplexClientInputPort<MessageType> createSimplexClientInputPort(String aHost, String aServerId, String aServerName, String aClientName);
}
