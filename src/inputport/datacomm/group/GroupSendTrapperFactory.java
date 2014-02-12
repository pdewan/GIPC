package inputport.datacomm.group;


import inputport.InputPort;

public interface GroupSendTrapperFactory<InMessageType, OutMessageType> {
	GroupSendTrapper<InMessageType, OutMessageType> createGroupSendTrapper(InputPort anInputPort, GroupNamingSender<OutMessageType>  aDestination);

}
