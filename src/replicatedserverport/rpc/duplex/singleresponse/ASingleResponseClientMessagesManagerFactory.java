package replicatedserverport.rpc.duplex.singleresponse;


public class ASingleResponseClientMessagesManagerFactory implements ClientMessagesManagerFactory {
	public ClientMessagesManager createClientMessagesManager() {
		return new ASingleResponseClientMessagesManager();
	}

}
