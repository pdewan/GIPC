package extraip;

import java.nio.ByteBuffer;

import inputport.InputPort;
import inputport.datacomm.ReceiveListener;

public class APrintingReceiveAndSendListener extends ASendConnectionListener implements ReceiveListener<ByteBuffer> {
	
	public APrintingReceiveAndSendListener(
			InputPort aClientInputPort) {
		super(aClientInputPort);
		// TODO Auto-generated constructor stub
	}
	public static String toString(ByteBuffer message) {
		byte[] msgBytes = new byte[message.limit() - message.position()];
		message.get(msgBytes);
		return new String(msgBytes);
	}
	@Override
	public void messageReceived(String clientName, ByteBuffer message) {		
		System.out.println("received message:" + toString(message) + " from " + clientName);
	}


}
