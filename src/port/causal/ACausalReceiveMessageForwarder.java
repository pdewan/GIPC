package port.causal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.InputPort;
import inputport.datacomm.AnAbstractReceiveTrapper;
import inputport.datacomm.ReceiveNotifier;
import util.trace.Tracer;



public class ACausalReceiveMessageForwarder extends AnAbstractReceiveTrapper<Object, Object> implements ConnectionListener{
//	ReceiveNotifier destination;
	VectorTimeStamp myVectorTimeStamp;
	List<VectorTimeStampedMessageWithSource> pendingReceivedMessages = new LinkedList();
	List<VectorTimeStampedMessageWithSource> readyReceivedMessages = new ArrayList();

	String myName;
	public ACausalReceiveMessageForwarder(InputPort anInputPort, ReceiveNotifier aReceiveNotifier, 
			VectorTimeStamp aVectorTimeStamp) {
		super (null, aReceiveNotifier);
		myVectorTimeStamp = aVectorTimeStamp;
		anInputPort.addConnectionListener(this);
		myName = anInputPort.getLocalName();
	}
//	@Override
//	public synchronized void notifyPortReceive(String remoteEnd, Object message) {
//		Message.info(this, this + " causally processing received message " + message + " from " + remoteEnd + " my vector TS " + myVectorTimeStamp);
//		if (message instanceof VectorTimeStampedMessage) {
//			VectorTimeStampedMessage vectorTimeStamptedMessage = 
//				(VectorTimeStampedMessage) message;
//			VectorTimeStampedMessageWithSource vectorTimeStampedMessageWithSource = 
//				new AVectorTimeStampedMessageWithSource(remoteEnd,vectorTimeStamptedMessage);
//			// add any missing users in my time stamp
//			myVectorTimeStamp.addUsers(vectorTimeStampedMessageWithSource.getVectorTimeStampedMessage().getVectorTimeStamp());			
//			addToPendingMessages(vectorTimeStampedMessageWithSource);	
//			processPendingMessages();
//			deliverReadyMessages();
//		} else {
//			Message.info(this, "Forwarding non vector time stampted message:" + message +  " from: " + remoteEnd);
//			nextStageReceiver.notifyPortReceive(remoteEnd, message);	
//		}
//	}
//	
	@Override
	public void notifyPortReceive(String remoteEnd, Object message) {
		Tracer.info(this, this + " causally processing received message " + message + " from " + remoteEnd + " my vector TS " + myVectorTimeStamp);
		if (message instanceof VectorTimeStampedMessage) {
			VectorTimeStampedMessage vectorTimeStamptedMessage = 
				(VectorTimeStampedMessage) message;	
			VectorTimeStampedMessageWithSource vectorTimeStampedMessageWithSource = 
				new AVectorTimeStampedMessageWithSource(remoteEnd,vectorTimeStamptedMessage);
			myVectorTimeStamp.addUsers(vectorTimeStamptedMessage.getVectorTimeStamp());		
			if (myVectorTimeStamp.isConcurrent(vectorTimeStamptedMessage.getVectorTimeStamp())) {
				Tracer.info(this, "Immediately readying concurrent message " + message + " from source " + remoteEnd + " my TS " + myVectorTimeStamp);
//				System.out.println("Immediately processing concurrent message " + message + " from source " + remoteEnd + " my TS " + myVectorTimeStamp);
				myVectorTimeStamp.addMessage(remoteEnd);
				readyReceivedMessages.add(vectorTimeStampedMessageWithSource);
//				processPendingMessages();
//				deliverReadyMessages();
//				deliverMessage(vectorTimeStampedMessageWithSource);				
			} else {
				addToPendingMessages(vectorTimeStampedMessageWithSource);				
			}
			processPendingMessages();
			deliverReadyMessages();						
		} else {
			Tracer.info(this, "Forwarding non vector time stampted message:" + message +  " from: " + remoteEnd);
			destination.notifyPortReceive(remoteEnd, message);	
		}
	}
	void deliverReadyMessages() {
		Tracer.info(this, "Delivering ready messages");
		for (VectorTimeStampedMessageWithSource message:readyReceivedMessages) {
			deliverMessage(message);
		}
		readyReceivedMessages.clear();		
		
	}
	void deliverMessage(VectorTimeStampedMessageWithSource receivedMessage) {		
//		myVectorTimeStamp.addMessage(receivedMessage.getSource());
		Tracer.info(this, "Delivering  message " + receivedMessage + " my TS " + myVectorTimeStamp);
		destination.notifyPortReceive(receivedMessage.getSource(), receivedMessage.getVectorTimeStampedMessage().getMessage());		

//		inQueue.processMessage(receivedMessage);		
	}
	
	void addToPendingMessages (VectorTimeStampedMessageWithSource receivedMessage) {
		int insertionIndex = 0;
		for (int i=0; i < pendingReceivedMessages.size(); i++) {
			if (receivedMessage.getVectorTimeStampedMessage().getVectorTimeStamp().compareTo( pendingReceivedMessages.get(i).getVectorTimeStampedMessage().getVectorTimeStamp()) > 0)
				insertionIndex++;
			else
				break;
		}		
		pendingReceivedMessages.add(insertionIndex, receivedMessage);	
		Tracer.info(this, "Added message:" + receivedMessage + "at position: " + insertionIndex);		
	}
	boolean processNextPendingMessage() {
		if (pendingReceivedMessages.size() == 0)
			return false;
		Tracer.info(this, "Processing message at front of queue");
		VectorTimeStampedMessageWithSource nextPendingMessage = pendingReceivedMessages.get(0);
		VectorTimeStamp nextVectorTimeStamp = nextPendingMessage.getVectorTimeStampedMessage().getVectorTimeStamp();
//		if (myVectorTimeStamp.isSucceededBy(nextPendingMessage.getVectorTimeStampedMessage().getVectorTimeStamp())) {
		if (myVectorTimeStamp.isConcurrent(nextVectorTimeStamp) || 
				myVectorTimeStamp.isSucceededBy(nextVectorTimeStamp)) {
			myVectorTimeStamp.addMessage(nextPendingMessage.getSource());
//			deliverMessage(nextPendingMessage);
			readyReceivedMessages.add(nextPendingMessage);
			pendingReceivedMessages.remove(0);
			Tracer.info(this, "Readyed message " + nextPendingMessage + " my new TS "  + myVectorTimeStamp);
			return true;
		} 
		Tracer.info(this, "Not ready for delivery next pending received message " + nextPendingMessage);
		Tracer.info(this, "Pending messages: " + pendingReceivedMessages);
		return false;
		
	}
	void  processPendingMessages() {
		if (processNextPendingMessage())
			processPendingMessages();		
	}
	@Override
	public void connected(String remoteEnd, ConnectionType aConnectionType) {
		Tracer.info(this, "Connected: " + remoteEnd);
		myVectorTimeStamp.addUser(remoteEnd);
		
	}
	@Override
	public void disconnected(String remoteEndName,
			boolean explicitDsconnection, String systemMessage, ConnectionType aConnectionType) {
		
	}
	@Override
	public void notConnected(String remoteEnd, String message, ConnectionType aConnectionType) {
		
	}

}
