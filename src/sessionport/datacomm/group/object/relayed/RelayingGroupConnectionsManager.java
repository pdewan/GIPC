package sessionport.datacomm.group.object.relayed;

import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.group.GroupOthersSender;
import sessionport.datacomm.duplex.object.relayed.RelayingDuplexConnectionsManager;

public interface RelayingGroupConnectionsManager extends 
	RelayingDuplexConnectionsManager, GroupNamingSender<Object>, GroupOthersSender<Object>
 {
	

}
