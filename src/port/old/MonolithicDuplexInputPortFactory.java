package port.old;


public interface MonolithicDuplexInputPortFactory  {
	public MonolithicDuplexServerInputPort createDuplexServerInputPort(String theServerId, String theServerName);
	MonolithicDuplexClientInputPort createDuplexClientInputPort(String theHost, String theServerId, String theClientName);

	
}
