package port.ot;

import util.session.MessageFilter;
import util.session.MessageProcessor;
import util.session.ReceivedMessage;

public class AnOTReceivedMessageQueuer implements MessageFilter<ReceivedMessage> {
	OTManager otManager;
	MessageProcessor<ReceivedMessage> receivedMessageQueue;
	public AnOTReceivedMessageQueuer(OTManager theOTManager) {
		otManager = theOTManager;
	}
	@Override
	public void filterMessage(ReceivedMessage message) {
		if (!message.isUserMessage() ) {
			receivedMessageQueue.processMessage(message);
			return;
		}		
		EditWithOTTimeStamp receivedTSEdit = 
			(EditWithOTTimeStamp) message.getUserMessage();			
		otManager.processReceivedMessage(receivedTSEdit);
		message.setUserMessage(receivedTSEdit.getTransformableEdit().getEdit());		
		receivedMessageQueue.processMessage(message);		
	}
	@Override
	public void setMessageProcessor(MessageProcessor<ReceivedMessage> theMesssageProcessor) {
		receivedMessageQueue = theMesssageProcessor;		
	}

}
