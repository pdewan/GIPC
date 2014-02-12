package port.delay;


public class AMessageWithDestination<MessageType> implements MessageWithDestination<MessageType>{
	MessageType data;
	String destination;	
	public AMessageWithDestination(MessageType aData, String aDestination) {
		super();
		this.data = aData;
		this.destination = aDestination;
	}
	public MessageType getData() {
		return data;
	}
	public void setData(MessageType newVal) {
		data = newVal;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String newVal) {
		destination = newVal;
	}
	
	@Override
	public String toString() {
		return super.toString() + " data " + data + " destination " + destination;
	}
	
}
