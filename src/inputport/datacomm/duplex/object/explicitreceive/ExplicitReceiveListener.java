package inputport.datacomm.duplex.object.explicitreceive;

import inputport.datacomm.ReceiveListener;


public interface ExplicitReceiveListener<MessageType> extends ReceiveListener <MessageType> {

//	public void  messageReceived(String remoteClientName, MessageType aessage);
	ReceiveReturnMessage<MessageType> getReceivedMessage();
	boolean hasBlockedThread();


	

}