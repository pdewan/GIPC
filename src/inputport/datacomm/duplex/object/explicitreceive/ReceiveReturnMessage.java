package inputport.datacomm.duplex.object.explicitreceive;

public interface ReceiveReturnMessage<MessageType> {
	MessageType getMessage();
	String getSource();
}
