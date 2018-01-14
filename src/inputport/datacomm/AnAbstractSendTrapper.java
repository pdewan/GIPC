package inputport.datacomm;

import util.trace.Tracer;
import util.trace.port.AConnectionEvent;
import util.trace.port.ConnectiontEventBus;

public abstract class AnAbstractSendTrapper<InMessageType, OutMessageType> implements SendTrapper<InMessageType, OutMessageType>{
	protected NamingSender<OutMessageType> destination;
	protected Object sharedSenderReceiverState;
	
	public AnAbstractSendTrapper(NamingSender<OutMessageType> aDestination) {
		setDestination(aDestination);
	}
	
	@Override
	public NamingSender<OutMessageType> getDestination() {
		return destination;
	}

	@Override
	public void setDestination(NamingSender<OutMessageType> aDestination) {
		Tracer.info(this, "Setting send destination to:" + aDestination);
		destination = aDestination;
		ConnectiontEventBus.newEvent(new AConnectionEvent(this, aDestination, true));
	}
	@Override
	public Object returnValue(String aDestination, Object aMessage) {
		
		return null;
	}
	public Object getSharedSenderReceiverState() {
		return sharedSenderReceiverState;
	}
	public void setSharedSenderReceiverState(Object newVal) {
		sharedSenderReceiverState = newVal;
	}
	@Override
	public void reply(String aSource, InMessageType aMessage) {
		send(aSource, aMessage);
	}
}
