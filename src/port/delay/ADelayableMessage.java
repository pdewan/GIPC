package port.delay;


public class ADelayableMessage implements DelayableMessage{
	MessageWithDestination messageDescription;
	MessageDeliverer forwarder;
	long time;
	public ADelayableMessage(MessageWithDestination aSendDescription,  MessageDeliverer aForwarder, long aTime) {
		messageDescription = aSendDescription;
		forwarder = aForwarder;
		time = aTime;
	}
	@Override
	public MessageWithDestination getMessageDescription() {
		return messageDescription;
	}
	@Override
	public void setMessageDescription(MessageWithDestination newVal) {
		messageDescription = newVal;
	}
	@Override
	public long getTime() {
		return time;
	}
	@Override
	public void setTime(long theDelay) {
		time = theDelay;
	}
	public MessageDeliverer getMessageDeliverer() {
		return forwarder;
	}
	public void setMessageDeliverer(MessageDeliverer forwarder) {
		this.forwarder = forwarder;
	}	
	
	@Override
	public String toString() {
		return super.toString() + " description " + messageDescription + " delay " + time;
	}
	
}
