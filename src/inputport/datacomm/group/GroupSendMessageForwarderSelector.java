package inputport.datacomm.group;

import inputport.InputPort;

public class GroupSendMessageForwarderSelector {
	static GroupSendTrapperFactory groupSendMessageForwaderFactory = new AGroupSendMessageForwarderFactory<>();
	
	public static GroupSendTrapper createGroupSendTrapper(
			InputPort anInputPort, GroupNamingSender aDestination) {
		return groupSendMessageForwaderFactory.createGroupSendTrapper(anInputPort, aDestination);
	}
	public static void setGroupSendMessageForwarderFactory(GroupSendTrapperFactory aFactory){
		groupSendMessageForwaderFactory = aFactory;
	}
	public static GroupSendTrapperFactory getGroupSendMessageForwarderFactory() {
		return groupSendMessageForwaderFactory;
	}
}
