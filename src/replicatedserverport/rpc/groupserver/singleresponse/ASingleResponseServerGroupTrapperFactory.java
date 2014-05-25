package replicatedserverport.rpc.groupserver.singleresponse;

import inputport.InputPort;
import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupTrapperFactory;
import replicatedserverport.rpc.duplex.singleresponse.ASingleResponseServerDuplexTrapperFactory;
import replicatedserverport.rpc.duplex.singleresponse.ServerMessagesManager;

public class ASingleResponseServerGroupTrapperFactory 
	extends ASingleResponseServerDuplexTrapperFactory 
	implements GroupTrapperFactory<Object, Object>{
	public ASingleResponseServerGroupTrapperFactory() {
		
	}
	protected ServerMessagesManager createServerMessageManager() {
		return new AGroupServerMessagesManager();
	}
//	ServerMessagesManager serverMessagesManager; 
//	
//	protected ServerMessagesManager createServerMessageManager() {
//		groupServerMessagesManager = new AGroupServerMessagesManager();
//		return groupServerMessagesManager;
//	}

	@Override
	public GroupSendTrapper<Object, Object> createGroupSendTrapper(
			InputPort anInputPort, GroupNamingSender<Object> destination) {
		ASingleResponseServerGroupSendTrapper retVal = 
			new ASingleResponseServerGroupSendTrapper(anInputPort, destination, (GroupServerMessagesManager) serverMessagesManager );
		serverMessagesManager.setBufferedMessageSender(retVal);
		anInputPort.addConnectionListener(serverMessagesManager);
		return retVal;
	}
}
