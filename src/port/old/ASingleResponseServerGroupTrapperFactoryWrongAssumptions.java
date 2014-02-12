package port.old;

import inputport.InputPort;
import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupTrapperFactory;
import replicatedserverport.rpc.duplex.singleresponse.ASingleResponseServerDuplexTrapperFactory;
import replicatedserverport.rpc.duplex.singleresponse.ServerMessagesManager;
import replicatedserverport.rpc.groupserver.singleresponse.ASingleResponseServerGroupSendTrapper;

public class ASingleResponseServerGroupTrapperFactoryWrongAssumptions 
	extends ASingleResponseServerDuplexTrapperFactory 
	implements GroupTrapperFactory<Object, Object>{
	GroupServerMessagesManager groupServerMessagesManager; 
	
	@Override
	protected ServerMessagesManager createServerMessageManager() {
		groupServerMessagesManager = new AGroupServerMessagesManager();
		return groupServerMessagesManager;
	}

	@Override
	public GroupSendTrapper<Object, Object> createGroupSendTrapper(
			InputPort anInputPort, GroupNamingSender<Object> destination) {
		return new ASingleResponseServerGroupSendTrapper(anInputPort, destination,(replicatedserverport.rpc.groupserver.singleresponse.GroupServerMessagesManager) groupServerMessagesManager );
	}
}
