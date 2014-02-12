package inputport.datacomm;

import inputport.InputPort;

public interface SendTrapperSetter<InMessageType, OutMessageType> extends InputPort {
	SendTrapper<InMessageType, OutMessageType> getSendTrapper();
	void setSendTrapper(SendTrapper<InMessageType, OutMessageType> newVal);

}
