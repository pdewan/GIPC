package port.old;

import java.nio.ByteBuffer;

import inputport.datacomm.group.GroupServerInputPort;




//import old.MonolithicDuplexServerInputPort;
//import old.MonolithicPrintingReplyingReceiveListener;


public class PrintingBroadcastingReceiverListener extends PrintingReplyingReceiveListener {
//	ByteBuffer outputBuffer = ByteBuffer.allocate(256);

	public PrintingBroadcastingReceiverListener(GroupServerInputPort thePort) {
		super(thePort);
	}
	GroupServerInputPort getGroupPort() {
		return (GroupServerInputPort) port;
	}
	@Override
	public void messageReceived(String clientName, ByteBuffer message) {
		super.messageReceived(clientName, message);
		String returnMessage = clientName + " sending to others";
//		outputBuffer.clear();
//		outputBuffer.put(returnMessage.getBytes());	
//		outputBuffer.flip();
		//message.rewind();
//		ByteBuffer returnMessage = message.duplicate();
//		returnMessage.rewind();
//		getGroupPort().sendAll(returnMessage);
		getGroupPort().sendOthers( ByteBuffer.wrap(returnMessage.getBytes()));
	
	}
}
