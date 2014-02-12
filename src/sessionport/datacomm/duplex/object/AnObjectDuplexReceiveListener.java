package sessionport.datacomm.duplex.object;
import inputport.datacomm.ReceiveListener;

public class AnObjectDuplexReceiveListener implements ReceiveListener<Object> {

	@Override
	public void messageReceived(String remoteClientName, Object message) {
//		if (message instanceof ByteBuffer) {
//			ByteBuffer messageBB = (ByteBuffer) message;
//			byte[] msgBytes = new byte[messageBB.limit() - messageBB.position()];
//			messageBB.get(msgBytes);
//			System.out.println("received message:" + new String(msgBytes) + " from " + remoteClientName);
//		} else
		System.out.println(remoteClientName + "<--" + message);
		
	}

}
