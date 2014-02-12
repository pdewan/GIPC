package port.ot;

import util.session.MessageFilter;
import util.session.MessageFilterCreator;
import util.session.SentMessage;

public class AnOTSentMessageQueuerCreator  implements MessageFilterCreator<SentMessage>{
	MessageFilter<SentMessage> sentMessageQueuer;
	OTManager otManager;
	public AnOTSentMessageQueuerCreator(OTManager theOTManager) {
		otManager = theOTManager;
		sentMessageQueuer =  new AnOTSentMessageQueuer(otManager);
	}
	@Override
	public MessageFilter<SentMessage> getMessageFilter() {
		return sentMessageQueuer;
	}

}
