package port.old;


public interface MonolithicClientInputPortFactory {
	MonolithicClientInputPort createDuplexClientInputPort(String theHost, String theServerId, String theClientName);
}
