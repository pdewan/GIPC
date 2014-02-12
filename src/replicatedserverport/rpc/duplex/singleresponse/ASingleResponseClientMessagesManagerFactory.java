package replicatedserverport.rpc.duplex.singleresponse;

import inputport.InputPort;

public class ASingleResponseClientMessagesManagerFactory implements ClientMessagesManagerFactory {
	public ClientMessagesManager createClientMessagesManager() {
		return new ASingleResponseClientMessagesManager();
	}

}
