package inputport.datacomm.group;

import java.util.HashSet;
import java.util.Set;

import inputport.datacomm.NamingSender;
import util.trace.port.AConnectionEvent;
import util.trace.port.ConnectiontEventBus;

public abstract class AnAbstractGroupToUniSendTrapper<InMessageType, OutMessageType>  
	implements GroupToUniSendTrapper<InMessageType, OutMessageType>{
	protected NamingSender<OutMessageType> destination;
	public AnAbstractGroupToUniSendTrapper(NamingSender<OutMessageType> aDestination) {
		setDestination(aDestination);
	}
	@Override
	public NamingSender<OutMessageType> getDestination() {
		return destination;
	}
	@Override
	public void setDestination(NamingSender<OutMessageType> newVal) {
		destination = newVal;
		ConnectiontEventBus.newEvent(
				new AConnectionEvent(this, newVal, true));
	}
	
	public void send(String aRemoteEnd, InMessageType aMessage) {
		Set<String> remoteEnds = new HashSet();
		send(remoteEnds, aMessage);
	}
}
