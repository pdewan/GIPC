package port.delay.example;
import inputport.datacomm.ReceiveListener;

public class AnEchoingObjectReceiveListener implements ReceiveListener<Object> {
//	GroupSessionPort<Object> groupSessionPort;
//	String RESPONSE_KEYWORD = "knows";
//	String myReponsePrefix;
//	public AnObjectReceiveListener(GroupSessionPort<Object> aPort) {
//		groupSessionPort = aPort;
//	}
	public AnEchoingObjectReceiveListener() {
		
	}
	@Override
	public void messageReceived(String remoteClientName, Object message) {
		String stringMessage = (String) message;
//		if (message instanceof ByteBuffer) {
//			ByteBuffer messageBB = (ByteBuffer) message;
//			byte[] msgBytes = new byte[messageBB.limit() - messageBB.position()];
//			messageBB.get(msgBytes);
//			System.out.println("received message:" + new String(msgBytes) + " from " + remoteClientName);
//		} else
		
//		System.out.println("Received  message " + message + " from "  + remoteClientName);
		System.out.println( message + "<--" + remoteClientName);

//		if (stringMessage.contains(RESPONSE_KEYWORD))
//			return;
//		String response = groupSessionPort.getLocalName() + " " + RESPONSE_KEYWORD + " " + message;
//		System.out.println("Broadcasting reponse " + response);
//		groupSessionPort.sendAll(response);
//		System.out.println("Finished Broadcasting response " + response);
		
	}

}
