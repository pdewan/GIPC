package inputport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;
import java.util.List;

import inputport.datacomm.AReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.simplex.buffer.AGenericSimplexBufferClientInputPort;
import util.trace.Tracer;
import util.trace.port.AConnectionEvent;
import util.trace.port.ConnectiontEventBus;
import util.trace.port.buffer.BufferClientChannelLocallyConnected;
import util.trace.port.buffer.BufferReceived;
import util.trace.port.buffer.ReplyDestinationAssociatedWithPort;



public class AGenericDuplexBufferClientInputPort<ChannelType> extends AGenericSimplexBufferClientInputPort<ChannelType> implements  DuplexBufferGenericClientInputPort<ChannelType>{
	
	ReceiveRegistrarAndNotifier aNotifier = new AReceiveRegistrarAndNotifier();
	ReceiveTrapper<ByteBuffer, ByteBuffer> receiveTrapper;
	String lastSender;
	EchoingBufferSender echoingBufferSender;

	// why no local sender?
	// should override the simplex send and send to local port
	public AGenericDuplexBufferClientInputPort( String aRemoteHostName, String aRemotePort, String aRemoteName, String aMyName) {
		super(aRemoteHostName, aRemotePort, aRemoteName, aMyName);
//		receiveTrapper = DuplexBufferClientIPTrapperSelector.getTrapperSelector().createReceiveTrapper(this, aNotifier);
		setReceiveTrapper (DuplexBufferClientIPTrapperSelector.getTrapperSelector().createReceiveTrapper(this, aNotifier));
	}	
	public void addReceiveListener(ReceiveListener<ByteBuffer> portReceiveListener) {
		aNotifier.addReceiveListener(portReceiveListener);
	}

	public void removeReceiveListener(ReceiveListener<ByteBuffer> portReceiveListener) {
		aNotifier.removeReceiveListener(portReceiveListener);
	}
	public void notifyPortReceive(String remoteEnd, ByteBuffer message) {
		receiveTrapper.notifyPortReceive(remoteEnd, message);
	}

	@Override
	public void messageReceived(String aSourceName, ByteBuffer aMessage) {
		Tracer.info(this, "Received message from driver");
		BufferReceived.newCase(this, aSourceName, serverName, aMessage, driver);
		notifyPortReceive(aSourceName, aMessage);
	}
	
	@Override
	public void reply(ByteBuffer message) {
		send(message);
	}
	
	@Override
	public void reply(String aRemoteEnd, ByteBuffer aMessage) {
		send(aRemoteEnd, aMessage);
	}
	
	
	public ReceiveTrapper<ByteBuffer, ByteBuffer> getReceiveTrapper() {
		return receiveTrapper;
	}

	public void setReceiveTrapper(ReceiveTrapper<ByteBuffer, ByteBuffer> aReceiveTrapper) {
		Tracer.info(this, "Set my receive trapper to:" + aReceiveTrapper);
		this.receiveTrapper = aReceiveTrapper;
		ConnectiontEventBus.newEvent(new AConnectionEvent(this, receiveTrapper, false));
	}
	
	@Override
	public String getSender() {
		return getLogicalRemoteEndPoint();
	}
	
	// actually this does not make much sense, a client port should not be sending
	// a message to itself, but let us keep it for testing purposes
	
	@Override
	public void connect() {
		if (serverName.equals(getLocalName())) {
			BufferClientChannelLocallyConnected.newCase(this, serverName, serverName);
			return;
		}
		super.connect();
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
		super.send(aRemoteName, aMessage);
	}

	@Override
	public void setSender(String newVal) {
		Tracer.info(this, "Surprisingly, setting client last sender to:" + newVal);
			lastSender = newVal;
			ReplyDestinationAssociatedWithPort.newCase(this, serverName, this);
	}
	@Override
	public List<ReceiveListener<ByteBuffer>> getReceiveListeners() {
		return aNotifier.getReceiveListeners();
	}
	
	protected EchoingBufferSender getOrCreateEchoingBufferSender() {
		if (echoingBufferSender == null)
			echoingBufferSender = new AnEchoingBufferSender(this);
		return  echoingBufferSender;
	}
	public ReceiveReturnMessage<ByteBuffer> receive() {
		return receive(getSender());		

	}
	@Override
	public ReceiveReturnMessage<ByteBuffer> receive(String aSource) {
		System.err.println("Receive not implemented");
		return null;
	}
	

}
