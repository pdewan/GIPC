package examples.gipc.counter.customization;

import java.io.Serializable;

public interface TimeStampedMessage extends Serializable {
	public Object getMessage() ;
	public void setMessage(Object newVal);
	public long getTimestamp() ;
	public void setTimestamp(long newVal);
}
