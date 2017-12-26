package replicatedserverport.datacomm.simplex;

import inputport.datacomm.AnAbstractSendTrapper;
import inputport.datacomm.group.GroupSender;
import trace.port.AConnectionEvent;
import trace.port.ConnectiontEventBus;

public abstract class AnAbstractMultiToReplicatedSendTrapper<InMessageType, OutMessageType> extends AnAbstractSendTrapper<InMessageType, OutMessageType>
	implements MultiToReplicatedSendTrapper<InMessageType, OutMessageType>{
	protected GroupSender<OutMessageType> destination;
	public AnAbstractMultiToReplicatedSendTrapper(GroupSender<OutMessageType> aDestination) {
		super(aDestination);
		setDestination (aDestination);
	}
	@Override
	public GroupSender<OutMessageType> getDestination() {
		return destination;
	}
	
	public void reply(String aSource, InMessageType aMessage) {
		send(aSource, aMessage);
	}

	@Override
	public void setDestination(GroupSender<OutMessageType> newVal) {
		destination = newVal;
		ConnectiontEventBus.newEvent(new AConnectionEvent(this, destination, true));
	}
	
}
