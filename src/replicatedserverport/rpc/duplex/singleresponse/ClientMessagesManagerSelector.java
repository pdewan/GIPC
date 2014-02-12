package replicatedserverport.rpc.duplex.singleresponse;

import inputport.InputPort;

public class ClientMessagesManagerSelector {
	static ClientMessagesManagerFactory factory;
	public static ClientMessagesManagerFactory getFactory() {
		return factory;
	}
	public static void setFactory(ClientMessagesManagerFactory aFactory) {
		factory = aFactory;
	}
	public static ClientMessagesManager createClientMessagesManager() {
		return factory.createClientMessagesManager();
	}

}
