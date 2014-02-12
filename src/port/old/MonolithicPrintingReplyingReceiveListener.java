package port.old;

import inputport.datacomm.simplex.buffer.ByteBufferSendListener;

import java.nio.ByteBuffer;




public class MonolithicPrintingReplyingReceiveListener extends MonolithicPrintingReceiveListener implements ByteBufferSendListener {
//	protected ByteBuffer outputBuffer = ByteBuffer.allocate(256);
	protected MonolithicDuplexServerInputPort port;
	public MonolithicPrintingReplyingReceiveListener(MonolithicDuplexServerInputPort thePort) {
		super(null);
		port = thePort;
	}

	@Override
	public void messageReceived(String clientName, ByteBuffer message, int length) {
		super.messageReceived(clientName, message, length);
//		ByteBuffer returnMessage = message.duplicate();
//		returnMessage.rewind();
//		port.send(clientName,  returnMessage);
		String returnMessage = clientName + " replying";
//		outputBuffer.clear();
//		outputBuffer.put(returnMessage.getBytes());
//		outputBuffer.flip();
		port.send(clientName, ByteBuffer.wrap(returnMessage.getBytes()));
	}
	
//
//	@Override
//	public void  disconnected(String remoteClientName, boolean explicitClose, String systemMessage) {
//		System.out.println(remoteClientName + " port closed, explicitClose " + explicitClose + " system message:" + systemMessage);
//		//return null;
//	}
//
//	@Override
//	public void connected(String remoteEnd) {
//		System.out.println("established connection to remote end:" + remoteEnd);		
//	}

	@Override
	public void messageSent(String aRemoteEnd, ByteBuffer message, int sentId) {
		byte[] msgBytes = message.array();
//		message.get(msgBytes);
		System.out.println("sent message:" + new String(msgBytes) + " with id " + sentId);
		//return null;
	}

}
