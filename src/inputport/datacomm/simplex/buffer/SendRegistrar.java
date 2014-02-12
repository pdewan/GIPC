package inputport.datacomm.simplex.buffer;



public interface SendRegistrar {
	void addSendListener(ByteBufferSendListener portSendListener);
	void removeSendListener(ByteBufferSendListener portSendListener);
	
}
