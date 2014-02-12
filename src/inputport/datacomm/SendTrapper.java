package inputport.datacomm;

import inputport.datacomm.duplex.NamingReplier;


public interface SendTrapper<InMessageType, OutMessageType>  extends
	NamingSender<InMessageType>, 
	NamingReplier<InMessageType>, 

	SharedSenderReceiverState<Object> {
	NamingSender<OutMessageType> getDestination();
	void setDestination(NamingSender<OutMessageType> aDestination);
	Object returnValue(String aDestination, Object aMessage);
	
}
