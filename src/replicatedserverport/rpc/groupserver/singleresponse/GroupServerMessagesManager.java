package replicatedserverport.rpc.groupserver.singleresponse;

import replicatedserverport.rpc.duplex.singleresponse.ServerMessagesManager;

public interface GroupServerMessagesManager 
		extends ServerMessagesManager{
	public boolean isGroupMessage(Object aMessage);
	public boolean addGroupMessage(Object aMessage) ;
	public void removeGroupMessage(Object aMessage) ;

}
