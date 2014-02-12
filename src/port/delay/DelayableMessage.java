package port.delay;


public interface DelayableMessage<MessageType>  extends Delayable{
	MessageWithDestination getMessageDescription();
	void setMessageDescription(MessageWithDestination newVal);
	MessageDeliverer getMessageDeliverer();
	void setMessageDeliverer(MessageDeliverer newVal);	
	
}
