package replicatedserverport.rpc.groupserver.singleresponse;

import java.util.ArrayList;
import java.util.List;

import replicatedserverport.rpc.duplex.singleresponse.AServerMessagesManager;

public class AGroupServerMessagesManager 
				extends AServerMessagesManager implements GroupServerMessagesManager{
	List<Object> groupMessages = new ArrayList();
	public boolean isGroupMessage(Object aMessage) {
		return groupMessages.contains(aMessage);		
	}
	public boolean addGroupMessage(Object aMessage) {
		return groupMessages.add(aMessage);	
	}	
	public void removeGroupMessage(Object aMessage) {
		groupMessages.remove(aMessage);
	}
}
