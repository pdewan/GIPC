package port.ot;

import util.misc.Common;
import util.session.MessageFilter;
import util.session.MessageProcessor;
import util.session.SentMessage;
import util.session.SentMessageType;

public class AnOTSentMessageQueuer implements MessageFilter<SentMessage> {
	OTManager otManager;
	MessageProcessor<SentMessage> sentMessageQueue;
	public AnOTSentMessageQueuer(OTManager theCausalityManager) {
		otManager = theCausalityManager;
	}

	@Override
	public synchronized void filterMessage(SentMessage message) {
		if (!message.isUserMessage() || message.getSentMessageType() != SentMessageType.Others) {
			sentMessageQueue.processMessage(message);
			return;
		}
		Edit edit = (Edit) message.getUserMessage();
		EditWithOTTimeStamp timeStampedEdit = otManager.processSentEdit(edit);
		message.setUserMessage(timeStampedEdit);			
//			Message.info ("OT Manager sending message:" + message.getUserMessage());
//			myOTTimeStamp.incrementLocalCount();
//			Edit edit = (Edit) message.getUserMessage();
//			TransformableEdit transformableEdit = new ATransformableEdit(edit, isServer);
//			EditWithOTTimeStamp timeStampedEdit = new AnEditWithOTTimeStamp(transformableEdit, myOTTimeStamp.copy());			
//			message.setUserMessage(timeStampedEdit);
//			Message.info("After send:" +myOTTimeStamp + " isServer:" + isServer);
//			Message.info("Enquing:" + message);

		//}
		sentMessageQueue.processMessage(message);
		SentMessage messageClone = (SentMessage) Common.deepCopy(message);
		otManager.storeSentMessage(messageClone);
	}

	@Override
	public void setMessageProcessor(MessageProcessor<SentMessage> theMesssageProcessor) {
		sentMessageQueue = theMesssageProcessor;		
	}

}
