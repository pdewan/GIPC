package port.delay;

import java.io.Serializable;

public interface MessageWithDestination<MessageType> extends Serializable{
	MessageType getData();
	void setData(MessageType newVal);
	public String getDestination();
	public void setDestination(String destintation);
}
