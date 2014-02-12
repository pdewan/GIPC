package port.ot;

import util.session.MessageFilter;
import util.session.MessageFilterCreator;
import util.session.ReceivedMessage;

public class AnOTReceivedMessageQueuerCreator implements
		MessageFilterCreator<ReceivedMessage> {
	MessageFilter<ReceivedMessage> receivedMessageQueuer;
	OTManager otManager;

	public AnOTReceivedMessageQueuerCreator(OTManager theOTManager) {
		otManager = theOTManager;
		receivedMessageQueuer = new AnOTReceivedMessageQueuer(otManager);
	}

	@Override
	public MessageFilter<ReceivedMessage> getMessageFilter() {
		return receivedMessageQueuer;
	}

}
