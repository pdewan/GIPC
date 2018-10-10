package inputport.datacomm;


import inputport.InputPort;

public interface SendTrapperFactory<InMessageType, OutMessageType> {
	SendTrapper<InMessageType, OutMessageType> createSendTrapper(InputPort anInputPort, NamingSender<OutMessageType>  aDestination);

//	SendTrapper<InMessageType, OutMessageType> getLastSendTrapper();

}
