package inputport.datacomm.duplex.object.explicitreceive;

public interface ExplicitSourceReceive<MessageType> {
	 ReceiveReturnMessage<MessageType> receive (String aSource);

}
