package inputport.datacomm.group;

import inputport.InputPort;

public class AnAscendingGroupSendMessageForwarderFactory<InMessageType> implements GroupSendTrapperFactory<InMessageType, InMessageType> {
	@Override
	public GroupSendTrapper<InMessageType, InMessageType> createGroupSendTrapper(
			InputPort anInputPort, GroupNamingSender<InMessageType> aDestination) {
		return new AnAscendingGroupSendMessageForwarder (aDestination);
	}

}
