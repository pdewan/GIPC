package variableserverport.datacomm.duplex.buffer;

import inputport.datacomm.ReceiveListener;

import java.nio.ByteBuffer;
import java.util.List;

import port.ParticipantChoice;

import sessionport.datacomm.duplex.buffer.example.ABufferDuplexSessionPortLauncher;
import variableserverport.datacomm.duplex.DuplexVariableServerClientPort;
import variableserverport.datacomm.simplex.buffer.AnAbstractSimplexBufferMultiServerPort;

public  abstract class AnAbstractDuplexDirectBufferVariableServerPort 
	extends AnAbstractSimplexBufferMultiServerPort implements DuplexVariableServerClientPort<ByteBuffer>
	
{

//	ReceiveRegistrarAndNotifier<ByteBuffer> receiveRegistrarAndNotifier = new AReceiveRegistrarAndNotifier();
//
	protected String lastSender;	
//
//	ReceiveTrapper<ByteBuffer, ByteBuffer> receiveTrapper;
	
	
	public AnAbstractDuplexDirectBufferVariableServerPort (
			 String aName, ParticipantChoice aChoice) {
		super(aName, aChoice);		
//		receiveTrapper = BufferDuplexSPFP2PTrapperSelector.getTrapperSelector().createReceiveTrapper(this, receiveRegistrarAndNotifier);
//		setReceiveTrapper(receiveTrapper);
			
	}	
//	protected ReceiveTrapper<ByteBuffer, ByteBuffer>  createReceiveTrapper() {
//		return BufferDuplexSPFP2PTrapperSelector.getTrapperSelector().createReceiveTrapper(this, receiveRegistrarAndNotifier);
//
//	}
	
//	@Override
//	public void addReceiveListener(
//			ReceiveListener<ByteBuffer> portReceiveListener) {
//		receiveRegistrarAndNotifier.addReceiveListener(portReceiveListener);
//	}

	@Override
	public List<ReceiveListener<ByteBuffer>> getReceiveListeners() {
		return receiveRegistrarAndNotifier.getReceiveListeners();
	}

	@Override
	public void notifyPortReceive(String remoteEnd, java.nio.ByteBuffer message) {
		lastSender = remoteEnd;
		super.notifyPortReceive(remoteEnd, message);
//		receiveTrapper.notifyPortReceive(remoteEnd, message);
	}
//	@Override
//	public void removeReceiveListener(
//			ReceiveListener<ByteBuffer> portReceiveListener) {
//		receiveRegistrarAndNotifier.removeReceiveListener(portReceiveListener);
//	}

//	@Override
//	public String getLocalName() {
//		return name;
//	}
//	

	@Override
	public void send(ByteBuffer message) {
		send(getSender(), message);
	}
	@Override
	public void reply(String aRemoteEnd, ByteBuffer aMessage) {
		send(aRemoteEnd, aMessage);
	}

	@Override
	public void reply(ByteBuffer message) {
		send(message);
	}
	@Override
	public String getSender() {
		return lastSender;
	}

	@Override
	public void setSender(String newVal) {
		lastSender = newVal;
	}

//	@Override
//	public ReceiveTrapper<ByteBuffer, ByteBuffer> getReceiveTrapper() {
//		return receiveTrapper;
//	}
//
//	@Override
//	public void setReceiveTrapper(ReceiveTrapper<ByteBuffer, ByteBuffer> receiveTrapper) {
//		Tracer.info(this, "Setting receive trapper to:" + receiveTrapper);
//		this.receiveTrapper = receiveTrapper;
//		DistEventsBus.newEvent(new AConnectionEvent(this, receiveTrapper, false));
//	}

	public static void main(String[] args) {
		ABufferDuplexSessionPortLauncher.launchSessionPartipant("9091", "Test Client", ParticipantChoice.MEMBER);		
	}

}
