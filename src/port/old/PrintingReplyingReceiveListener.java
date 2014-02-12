package port.old;

import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;

import java.nio.ByteBuffer;

import extraip.APrintingReceiveAndSendListener;





public class PrintingReplyingReceiveListener extends APrintingReceiveAndSendListener implements ByteBufferSendListener {
	protected DuplexServerInputPort port;
	public PrintingReplyingReceiveListener(DuplexServerInputPort thePort) {
		super(thePort);
		port = thePort;
	}

	@Override
	public void messageReceived(String clientName, ByteBuffer message) {
		String returnMessage = "Acking message " + toString(message) + " from " + clientName;
		System.out.println("Echoing message: " + returnMessage);
		port.send(clientName, ByteBuffer.wrap(returnMessage.getBytes()));
	}

	@Override
	public void messageSent(String aRemoteEnd, ByteBuffer message, int sentId) {
		byte[] msgBytes = message.array();
		System.out.println("sent message:" + new String(msgBytes) + " with id " + sentId);
	}

}
