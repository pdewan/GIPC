package examples.gipc.counter.customization;

import java.util.Date;

public class ATimeStampedMessage {
	protected Object message;
	protected long timestamp;
	
	public ATimeStampedMessage(Object aMessage, long aTimestamp) {
		super();
		this.message = aMessage;
		this.timestamp = aTimestamp;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object newVal) {
		this.message = newVal;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long newVal) {
		this.timestamp = newVal;
	}
	public String toString() {
		return message + "@" + timestamp + "(" + new Date(timestamp) + ")";
	}
	
	

}
