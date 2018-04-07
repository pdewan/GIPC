package inputport.datacomm.duplex.object;

import inputport.datacomm.AReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.simplex.object.ASimplexObjectClientInputPort;
import inputport.datacomm.simplex.object.ClientObjectTrapperSelector;
import inputport.datacomm.simplex.object.ObjectTranslatingIPTrapperSelector;

import java.nio.ByteBuffer;
import java.util.List;

import util.trace.port.AConnectionEvent;
import util.trace.port.ConnectiontEventBus;






public class ADuplexObjectClientInputPort extends ASimplexObjectClientInputPort implements DuplexClientInputPort<Object>, ReceiveListener<ByteBuffer>{

//	ReceiveRegistrarAndNotifier receveRegistrarAndNotifier = new AReceiveRegistrarAndNotifier();
	ReceiveRegistrarAndNotifier receveRegistrarAndNotifier = createReceiveRegistrarAndNotifier();

	DuplexClientInputPort<ByteBuffer> bbDuplexClientInputPort;
	ReceiveNotifier<ByteBuffer> deserializer;
	ReceiveTrapper<Object, Object> receiveObjectForwarder;
	
	public ADuplexObjectClientInputPort(DuplexClientInputPort<ByteBuffer> aBBClientInputPort) {
		super(aBBClientInputPort);
		bbDuplexClientInputPort = aBBClientInputPort;
		bbDuplexClientInputPort.addReceiveListener(this);
		receiveObjectForwarder = ClientObjectTrapperSelector.getTrapperSelector().createReceiveTrapper(this, receveRegistrarAndNotifier);
		deserializer = ObjectTranslatingIPTrapperSelector.getTrapperSelector().createReceiveTrapper(this, receiveObjectForwarder);
		setReceiveTrapper(receiveObjectForwarder);
		
		//		deserializer = GlobalState.getObjectTranslatingIPTrapperSelector().createReceiveTrapper(this, receiveObjectForwarder);

	}
	protected ReceiveRegistrarAndNotifier<Object> createReceiveRegistrarAndNotifier() {
		return new AReceiveRegistrarAndNotifier<Object>();
	}

	@Override
	public void reply(Object message) {
		send(message);
		
	}

	@Override
	public void addReceiveListener(
			ReceiveListener<Object> portReceiveListener) {
		receveRegistrarAndNotifier.addReceiveListener(portReceiveListener);
		
	}

	@Override
	public void removeReceiveListener(
			ReceiveListener<Object> portReceiveListener) {
		receveRegistrarAndNotifier.removeReceiveListener(portReceiveListener);
		
	}

	@Override
	public void notifyPortReceive(String remoteEnd, Object message) {
		receiveObjectForwarder.notifyPortReceive(remoteEnd,
				message);
		
	}

//	@Override
	public DuplexClientInputPort<ByteBuffer> getDuplexBufferClientInputPort() {
		// TODO Auto-generated method stub
		return bbDuplexClientInputPort;
	}

	@Override
	public void messageReceived(String remoteClientName, ByteBuffer message) {
		deserializer.notifyPortReceive(remoteClientName, message);
	
	}

	@Override
	public ReceiveTrapper<Object, Object> getReceiveTrapper() {
		return receiveObjectForwarder;
	}

	@Override
	public void setReceiveTrapper(ReceiveTrapper<Object, Object> newVal) {
		receiveObjectForwarder = newVal;	
		ConnectiontEventBus.newEvent(new AConnectionEvent(this, deserializer, false)); // this is strange
	}
	
	// repeated from single directional object server input port
	static {
//		GlobalState.getObjectTranslatingIPTrapperSelector().
//			setReceiveTrapperFactory(new ADeserializingForwarderFactory());
//		ObjectTranslatingIPTrapperSelector.getTrapperSelector().setReceiveTrapperFactory(new ADeserializingForwarderFactory());

	}

	@Override
	public String getSender() {
		return bbDuplexClientInputPort.getSender();
	}

	@Override
	public void setSender(String newVal) {
		bbDuplexClientInputPort.setSender(newVal);
	}

	@Override
	public List<ReceiveListener<Object>> getReceiveListeners() {
		// TODO Auto-generated method stub
		return receveRegistrarAndNotifier.getReceiveListeners();
	}
	@Override
	public void reply(String aRemoteEnd, Object aMessage) {
		send(aRemoteEnd, aMessage);
	}	
	public ReceiveReturnMessage<Object> receive() {
		String aLastSender = getSender();
		if (aLastSender == null) {
//			System.out.println("Paramaterless receive returns null as no message sent so far");
			return null;
		}
		return receive(getSender());		

	}
	@Override
	public ReceiveReturnMessage<Object> receive(String aSource) {
		System.err.println("Receive not implemented");
		return null;
	}
}
