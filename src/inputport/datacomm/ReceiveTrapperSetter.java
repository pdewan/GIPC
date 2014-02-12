package inputport.datacomm;

import inputport.InputPort;

public interface ReceiveTrapperSetter<InMessageType, OutMessageType>  extends InputPort{
	ReceiveTrapper<InMessageType, OutMessageType> getReceiveTrapper();
	void setReceiveTrapper(ReceiveTrapper<InMessageType, OutMessageType> newVal);

}
