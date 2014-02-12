package inputport.datacomm.group;


import inputport.InputPort;
import inputport.datacomm.NamingSender;

public interface GroupToUniSendTrapperFactory<InMessageType, OutMessageType> {
	GroupToUniSendTrapper<InMessageType, OutMessageType> createGroupToUniSendTrapper(InputPort anInputPort, NamingSender<OutMessageType>  aDestination);

}
