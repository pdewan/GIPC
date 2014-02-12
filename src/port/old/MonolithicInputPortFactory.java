package port.old;


public interface MonolithicInputPortFactory  {
	public MonolithicServerInputPort createServerInputPort(String theServerId, String theServerName);
	MonolithicClientInputPort  createClientInputPort(String theHost, String theServerId, String theClientName);

	
}
