package replicatedserverport.rpc.duplex.preferredresponse;

import replicatedserverport.rpc.duplex.singleresponse.ClientMessagesManager;
import replicatedserverport.rpc.duplex.singleresponse.ClientMessagesManagerFactory;


public class APreferredResponseClientMessagesManagerFactory implements ClientMessagesManagerFactory {
	String name;
	public APreferredResponseClientMessagesManagerFactory(String aName) {
		name = aName;
		
	}
	public ClientMessagesManager createClientMessagesManager() {
		return new APreferredResponseClientMessagesManager(name);
	}

}
