package inputport.datacomm.group;

import inputport.datacomm.NamingSender;

public interface GroupToUniSendTrapper<InMessageType, OutMessageType> extends 
	GroupNamingSender<InMessageType> {
	NamingSender<OutMessageType> getDestination();
	void setDestination (NamingSender<OutMessageType> newVal);	

}
