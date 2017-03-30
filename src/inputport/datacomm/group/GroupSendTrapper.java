package inputport.datacomm.group;

import java.util.Set;

public interface GroupSendTrapper<InMessageType, OutMessageType> extends 
	GroupNamingSender<InMessageType> {
//	GroupSender<InMessageType> {
	GroupNamingSender<OutMessageType> getDestination();
	void setDestination (GroupNamingSender<OutMessageType> newVal);
//	GroupSender<OutMessageType> getDestination();
//	void setDestination (GroupSender<OutMessageType> newVal);
//	Object[] getSendReturnValue(Set<String> aClientNamesSet, Object aMessage);	
	Object getSendReturnValue(Set<String> aClientNamesSet, Object aMessage);	


}
