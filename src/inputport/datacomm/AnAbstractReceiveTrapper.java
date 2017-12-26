package inputport.datacomm;
import inputport.InputPort;
import trace.port.AConnectionEvent;
import trace.port.ConnectiontEventBus;
import util.trace.Tracer;

public abstract class AnAbstractReceiveTrapper<InMessageType, OutMessageType> implements ReceiveTrapper<InMessageType, OutMessageType> {
	protected ReceiveNotifier<OutMessageType> destination;
	protected InputPort inputPort;
	protected Object sharedSenderReceiverState;
	
	public AnAbstractReceiveTrapper(InputPort anInputPort, ReceiveNotifier<OutMessageType> aDestination){
		inputPort = anInputPort;
		setDestination(aDestination);
	}	
	@Override
	public ReceiveNotifier<OutMessageType> getDestination() {
		return destination;
	}
	@Override
	public void setDestination(ReceiveNotifier<OutMessageType> aDestination) {
		Tracer.info(this, "Setting receive destination to:" + aDestination);
		destination = aDestination;
		ConnectiontEventBus.newEvent(new AConnectionEvent(this, aDestination, false));
	}	
	public Object getSharedSenderReceiverState() {
		return sharedSenderReceiverState;
	}
	public void setSharedSenderReceiverState(Object newVal) {
		sharedSenderReceiverState = newVal;
	}
}
