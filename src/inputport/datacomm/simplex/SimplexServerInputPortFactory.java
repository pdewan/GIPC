package inputport.datacomm.simplex;




public interface SimplexServerInputPortFactory<MessageType> {
	public SimplexServerInputPort<MessageType> createSimplexServerInputPort(String theServerId, String theServerName);
	
}
