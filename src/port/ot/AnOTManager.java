package port.ot;

import java.util.ArrayList;
import java.util.List;

import util.models.BoundedBuffer;
import util.session.ReceivedMessage;
import util.session.SentMessage;
import util.trace.Tracer;

public class AnOTManager implements OTManager {
	BoundedBuffer<ReceivedMessage> inQueue;
	BoundedBuffer<SentMessage> outQueue;
	boolean isServer ;
	OTTimeStamp myOTTimeStamp = new AnOTTimeStamp();	
	//Communicator communicator;
	List<SentMessage> sentMessages = new ArrayList();
	OperationTransformer operationTransformer = AnOperationTransfomerSelector.getOperationTransformer();
	
	public AnOTManager (boolean theIsServer) {
		isServer = theIsServer;
	}	

//	@Override
//	public synchronized void put(ReceivedMessage message) {
//		if (message.isUserMessage()) {
//			//Message.info ("OT Manager receiving message:" + message.getUserMessage());
//			EditWithOTTimeStamp receivedTSEdit = 
//				(EditWithOTTimeStamp) message.getUserMessage();			
//			processReceivedMessage(receivedTSEdit);
//			message.setUserMessage(receivedTSEdit.getTransformableEdit().getEdit());
//			//myOTTimeStamp.incrementRemoteCount();
//			//Message.info("After received:" +myOTTimeStamp + " isServer:" + isServer);
//
//		}
//		if (inQueue != null)
//			inQueue.put(message);			
//				
//	}
	
	public void processReceivedMessage(EditWithOTTimeStamp receivedTSEdit) {
		Tracer.info("Transforming:" + receivedTSEdit);
		int firstConcurrentMessageIndex = sentMessages.size();
		for (int i = 0; i < sentMessages.size(); i++) {
			EditWithOTTimeStamp sentTSEdit = (EditWithOTTimeStamp )sentMessages.get(i).getUserMessage();
			if (sentTSEdit.getOTTimeStamp().isConcurrent(receivedTSEdit.getOTTimeStamp())) {
				Tracer.info("found concurrent sent message " + sentTSEdit);
				firstConcurrentMessageIndex = i;
				break;
			}
		}
		for (int deleteNum = 0; deleteNum < firstConcurrentMessageIndex; deleteNum++ ) {
			sentMessages.remove(0);			
		}
		TransformableEdit receivedTransformableEdit = receivedTSEdit.getTransformableEdit();
		Tracer.info("Transforming repeatedly received:" + receivedTSEdit);
		//myOTTimeStamp.incrementRemoteCount();
		for (SentMessage sentMessage:sentMessages) {
			EditWithOTTimeStamp sentTSEdit = (EditWithOTTimeStamp )sentMessage.getUserMessage();
			Tracer.info("transforming wrt buffered:" + sentTSEdit);
			TransformableEdit sentTransformableEdit = sentTSEdit.getTransformableEdit();
			Edit transformedReceived = operationTransformer.transform(
						receivedTransformableEdit, sentTransformableEdit);
			Edit transformedSent = operationTransformer.transform(sentTransformableEdit, receivedTransformableEdit);
			sentTransformableEdit.setEdit(transformedSent);

			receivedTransformableEdit.setEdit(transformedReceived);
			sentTSEdit.getOTTimeStamp().incrementRemoteCount();
			receivedTSEdit.getOTTimeStamp().incrementRemoteCount();	
			Tracer.info("buffered transformed to:" + sentTSEdit);
			Tracer.info("Received transformed to:" + receivedTSEdit);
		}
		myOTTimeStamp.incrementRemoteCount();
		Tracer.info("Transformed:" + receivedTSEdit);
		Tracer.info("My Time Stamp:" + myOTTimeStamp);
	}
	
	

//	@Override
//	public synchronized void put(SentMessage message) {
//		if (message.isUserMessage()) {
//			Edit edit = (Edit) message.getUserMessage();
//			EditWithOTTimeStamp timeStampedEdit = processSentEdit(edit);
//			message.setUserMessage(timeStampedEdit);			
////			Message.info ("OT Manager sending message:" + message.getUserMessage());
////			myOTTimeStamp.incrementLocalCount();
////			Edit edit = (Edit) message.getUserMessage();
////			TransformableEdit transformableEdit = new ATransformableEdit(edit, isServer);
////			EditWithOTTimeStamp timeStampedEdit = new AnEditWithOTTimeStamp(transformableEdit, myOTTimeStamp.copy());			
////			message.setUserMessage(timeStampedEdit);
////			Message.info("After send:" +myOTTimeStamp + " isServer:" + isServer);
////			Message.info("Enquing:" + message);
//
//		}
//		if (outQueue != null)
//			outQueue.put(message);
//		SentMessage messageClone = (SentMessage) Misc.deepCopy(message);
//		sentMessages.add(messageClone);
//	}
	public void storeSentMessage(SentMessage message) {
		sentMessages.add(message);
	}
	public EditWithOTTimeStamp processSentEdit(Edit edit ) {
		Tracer.info ("Raw Edit:" + edit);
		myOTTimeStamp.incrementLocalCount();
		TransformableEdit transformableEdit = new ATransformableEdit(edit, isServer);
		EditWithOTTimeStamp timeStampedEdit = new AnEditWithOTTimeStamp(transformableEdit, myOTTimeStamp.copy());			
		return timeStampedEdit;
		
	}
	

//	@Override
//	public void setReceivedMessageQueue(BoundedBuffer<ReceivedMessage> theBuffer) {
//		inQueue = theBuffer;
//	}
//
//	@Override
//	public void setSentMessageQueue(BoundedBuffer<SentMessage> theBuffer) {
//		outQueue = theBuffer;
//	}

	
//	public void setClients(Map<MessageReceiver, String> theClients)	{
//		Collection<String> values = theClients.values();
//		for (String clientName:values) {
//			System.out.println("TS for" + clientName);
//			myOTTimeStamp.addUser(clientName);
//		}
//		
//	}
	
	
	

}
