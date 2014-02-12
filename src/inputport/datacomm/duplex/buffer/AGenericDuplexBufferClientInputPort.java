package inputport.datacomm.duplex.buffer;

import inputport.datacomm.AReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.simplex.buffer.AGenericSimplexBufferClientInputPort;

import java.nio.ByteBuffer;
import java.util.List;

import port.trace.AConnectionEvent;
import port.trace.ConnectiontEventBus;
import util.trace.Tracer;



public class AGenericDuplexBufferClientInputPort<ChannelType> extends AGenericSimplexBufferClientInputPort<ChannelType> implements  DuplexBufferGenericClientInputPort<ChannelType>{
	
	ReceiveRegistrarAndNotifier aNotifier = new AReceiveRegistrarAndNotifier();
	ReceiveTrapper<ByteBuffer, ByteBuffer> receiveTrapper;
	String lastSender;
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
	


	@Override
	public void setSender(String newVal) {
		Tracer.info(this, "Surprinsingly, setting client last sender to:" + newVal);
			lastSender = newVal;
	}
	@Override
	public List<ReceiveListener<ByteBuffer>> getReceiveListeners() {
		return aNotifier.getReceiveListeners();
	}
	
	

}
