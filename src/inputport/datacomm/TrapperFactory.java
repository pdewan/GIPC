package inputport.datacomm;


public interface TrapperFactory<InMessageType, OutMessageType>  extends 
	SendTrapperFactory<InMessageType, OutMessageType>,
	ReceiveTrapperFactory<OutMessageType, InMessageType>{

}
