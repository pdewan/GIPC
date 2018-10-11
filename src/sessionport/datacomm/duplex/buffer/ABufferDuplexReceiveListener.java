package sessionport.datacomm.duplex.buffer;
import java.nio.ByteBuffer;

import inputport.datacomm.ReceiveListener;

public class ABufferDuplexReceiveListener implements ReceiveListener<ByteBuffer> {

	@Override
	public void messageReceived(String remoteClientName, ByteBuffer message) {
		byte[] msgBytes = new byte[message.limit() - message.position()];
		message.get(msgBytes);
		System.out.println("received message:" + new String(msgBytes) + " from " + remoteClientName);
		
	}

}
