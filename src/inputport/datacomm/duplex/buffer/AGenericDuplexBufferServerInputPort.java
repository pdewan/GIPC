package inputport.datacomm.duplex.buffer;

import inputport.datacomm.SendToUnconnectedPortException;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.duplex.NoMessageReceivedByResponderException;
import inputport.datacomm.simplex.buffer.AGenericSimplexBufferServerInputPort;
import inputport.datacomm.simplex.buffer.ASendRegistrarAndNotifier;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;
import inputport.datacomm.simplex.buffer.SendRegistrarAndNotifier;

import java.nio.ByteBuffer;

import port.trace.AConnectionEvent;
import port.trace.ConnectiontEventBus;
import util.trace.Tracer;

public class AGenericDuplexBufferServerInputPort<RequestChannelType, MessageChannelType> extends AGenericSimplexBufferServerInputPort<RequestChannelType, MessageChannelType> 
             implements DuplexBufferGenericServerInputPort<RequestChannelType, MessageChannelType> {
	SendTrapper<ByteBuffer, ByteBuffer> sendTrapper;
//	protected String lastSender = null;
	SendRegistrarAndNotifier sendRegistrarAndNotifier = new ASendRegistrarAndNotifier();
	EchoingBufferSender echoingBufferSender;
	
	
	DuplexServerInputPortDriver<RequestChannelType, MessageChannelType> duplexDriver;

	public SendTrapper<ByteBuffer, ByteBuffer> getSendTrapper() {
		return sendTrapper;
	}
	public void setSendTrapper(SendTrapper<ByteBuffer, ByteBuffer> aSendTrapper) {
		Tracer.info(this, "Setting my send trapper:" + aSendTrapper);
		this.sendTrapper = aSendTrapper;
		ConnectiontEventBus.newEvent(new AConnectionEvent(this, aSendTrapper, true));
	}
	public AGenericDuplexBufferServerInputPort( String theMyName, String anId) {
		super(theMyName, anId);
	}
	@Override
	public void setDriver(DuplexServerInputPortDriver<RequestChannelType, MessageChannelType> theDriver) {
		super.setDriver(theDriver);
		duplexDriver = theDriver;
//		sendTrapper = DuplexBufferServerIPTrapperSelector.getTrapperSelector().createSendTrapper(this, theDriver);
		setSendTrapper (DuplexBufferServerIPTrapperSelector.getTrapperSelector().createSendTrapper(this, theDriver));

	}	

	@Override
	public void reply(ByteBuffer message) {
		if (getSender() == null) throw new NoMessageReceivedByResponderException();
		Tracer.info(this, "Replying:" + message);
		send(getSender(), message);
	}
//	@Override
//	public String getLastSender() {
//		return lastSender;
//	}
//	@Override
//	public void setLastSender(String newVal) {
//		if (newVal.equals(lastSender)) return;
//		Tracer.info(this, "Setting reply receiver:" + lastSender);
//		lastSender = newVal;
//	}
	
//	public void messageReceived(String aClientName, ByteBuffer aMessage) {
//		super.messageReceived(aClientName, aMessage);
//	}

	protected EchoingBufferSender getOrCreateEchoingBufferSender() {
		if (echoingBufferSender == null)
			echoingBufferSender = new AnEchoingBufferSender(this);
		return  echoingBufferSender;
	}

	@Override
	public void send(String aRemoteName, ByteBuffer aMessage) {
		if (aRemoteName.equals(getLocalName())) {
//			messageReceived(aRemoteName, aMessage);
//			notifyPortSend(aRemoteName, aMessage, -1); // no channel wrie was actually done, this is to inform the serializer pool
//			getOrCreateEchoingBufferSender().localSend(aMessage);
			getOrCreateEchoingBufferSender().enqueLocalSend(aMessage);

			return;
		}
		if (!isConnected(aRemoteName)) {
			
			String message = "Ignoring attempt to send " + aMessage + " to " + aRemoteName + " before connection completely established or after other end disconnected";
//			Tracer.error(message);
			throw new SendToUnconnectedPortException(message);
//			return;
		}
		Tracer.info(this, "Asking driver to send  message:" + aMessage + " to " + aRemoteName);
		sendTrapper.send(aRemoteName, aMessage);

	}
//	@Override
//	public void notifyPortReceive (String remoteEnd, ByteBuffer message) {
//		setLastSender(remoteEnd);
//		super.notifyPortReceive(remoteEnd, message);
//	}
	
	public void addSendListener(ByteBufferSendListener portSendListener) {
		sendRegistrarAndNotifier.addSendListener(portSendListener);
	}
	@Override
	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		sendRegistrarAndNotifier.notifyPortSend(aRemoteEnd, message, sendId);
	}
	@Override
	public void messageSent(String aRemoteEnd, ByteBuffer aMessage, int aSendId) {
		Tracer.info(this, "Received sent message notification from driver");
		notifyPortSend(aRemoteEnd, aMessage, aSendId);
	}
	public void removeSendListener(ByteBufferSendListener portSendListener) {
		sendRegistrarAndNotifier.removeSendListener(portSendListener);
	}
	@Override
	public void send(ByteBuffer message) {
		reply(message);
		
	}
	@Override
	public void reply(String aRemoteEnd, ByteBuffer aMessage) {
		send(aRemoteEnd, aMessage);
	}
	
		

	

	

}
