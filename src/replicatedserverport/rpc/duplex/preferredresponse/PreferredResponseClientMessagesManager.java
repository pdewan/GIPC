package replicatedserverport.rpc.duplex.preferredresponse;

import replicatedserverport.rpc.duplex.singleresponse.ClientMessagesManager;

public interface PreferredResponseClientMessagesManager extends ClientMessagesManager {
	void setName (String aName);

}
