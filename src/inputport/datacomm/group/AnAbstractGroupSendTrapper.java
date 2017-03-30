package inputport.datacomm.group;

import java.util.HashSet;
import java.util.Set;

import port.trace.AConnectionEvent;
import port.trace.ConnectiontEventBus;
import util.trace.Tracer;


public abstract  class AnAbstractGroupSendTrapper<InMessageType, OutMessageType>  
	implements GroupSendTrapper<InMessageType, OutMessageType>{
	protected GroupNamingSender<OutMessageType> destination;

//	protected GroupNamingSender<OutMessageType> destination;
	
	public AnAbstractGroupSendTrapper(GroupNamingSender<OutMessageType> aDestination) {
		setDestination(aDestination);
	}
	

//	@Override
//	public GroupNamingSender<OutMessageType> getDestination() {
//		return destination;
//	}
	@Override
	public GroupNamingSender<OutMessageType> getDestination() {
		return destination;
	}

	@Override
	public void setDestination(GroupNamingSender<OutMessageType> newVal) {
		destination = newVal;
		ConnectiontEventBus.newEvent(
			new AConnectionEvent(this, newVal, true));
	}
//	@Override
//	public Object[] getSendReturnValue(Set<String> aClientNamesSet, Object aMessage) {
//		return null;
//	}
	@Override
	public Object getSendReturnValue(Set<String> aClientNamesSet, Object aMessage) {
		return null;
	}
	@Override
	public void send(String clientName, InMessageType message) {
		Tracer.info(this, "Converting single to multiple send");
		Set<String> clientNames = new HashSet();
		clientNames.add(clientName);
		send (clientNames, message);
		
	}
	

	
}
