package port.jitter;

public class AMessageWithTimeStamp implements MessageWithTimeStamp {
	Object message;
	long timeStamp;
	
	public AMessageWithTimeStamp(Object aMessage, long aTimeStamp) {
		message = aMessage;
		timeStamp = aTimeStamp;
	}
	@Override
	public Object getMessage() {
		return message;
	}

	@Override
	public long getTimeStamp() {
		return timeStamp;
	}
	@Override
	public void setMessage(Object aMessage) {
		message = aMessage;		
	}
	@Override
	public void setTimeStamp(long aTimeStamp) {
		timeStamp = aTimeStamp;		
	}

}
