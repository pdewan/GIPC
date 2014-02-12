package inputport.datacomm.duplex;

import inputport.datacomm.Sender;



public interface DuplexSender<MessageType> extends Sender<MessageType>, Replier<MessageType>{
//	void reply(MessageType message);

}
