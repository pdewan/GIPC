package replicatedserverport.rpc.duplex.preferredresponse;

import replicatedserverport.rpc.duplex.singleresponse.ClientMessagesManagerSelector;
import replicatedserverport.rpc.duplex.singleresponse.SingleResponseReplicatedClientServerUtlity;

public class PreferredResponseReplicatedClientServerUtlity extends SingleResponseReplicatedClientServerUtlity{

	
			public static void setPreferredResponseClientFactories(String aName) {
				setPreferredResponseClientManagerFactory(aName);
				setSingleResponseClientTrapperFactories();

			}

			public static void setPreferredResponseClientManagerFactory(String aName ) {				
				ClientMessagesManagerSelector.setFactory(new APreferredResponseClientMessagesManagerFactory(aName));			

			}
			

}
