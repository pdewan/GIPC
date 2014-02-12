package port.jitter;

import java.io.Serializable;

public interface MessageWithTimeStamp  extends Serializable {
	public Object getMessage();
	public long getTimeStamp();
	public void setMessage(Object aMessage);
	public void setTimeStamp(long aTimeStamp);
}
