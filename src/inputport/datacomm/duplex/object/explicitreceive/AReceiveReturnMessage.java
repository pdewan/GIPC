package inputport.datacomm.duplex.object.explicitreceive;

public class AReceiveReturnMessage<MessageType> implements ReceiveReturnMessage<MessageType> {
	String source;
	MessageType message;
	public AReceiveReturnMessage(String aSource, MessageType aMessage) {
		source = aSource;
		message = aMessage;		
	}
	public String getSource() {
		return source;
	}
	public MessageType getMessage() {
		return message;
	}
	public String toString() {
		return source + ":" + message;
	}

}
