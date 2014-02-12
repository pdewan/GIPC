package port.delay.example;
import inputport.datacomm.ReceiveListener;
import sessionport.datacomm.group.GroupSessionPort;

public class AnObjectGroupSendingReceiveListener extends AnEchoingObjectReceiveListener implements ReceiveListener<Object> {
	GroupSessionPort<Object> groupSessionPort;
	String RESPONSE_KEYWORD = "knows";
//	String myReponsePrefix;
	public AnObjectGroupSendingReceiveListener(GroupSessionPort<Object> aPort) {
		groupSessionPort = aPort;
	}
	@Override
	public void messageReceived(String remoteClientName, Object message) {
		super.messageReceived(remoteClientName, message);
		String stringMessage = (String) message;
//		if (message instanceof ByteBuffer) {
//			ByteBuffer messageBB = (ByteBuffer) message;
//			byte[] msgBytes = new byte[messageBB.limit() - messageBB.position()];
//			messageBB.get(msgBytes);
//			System.out.println("received message:" + new String(msgBytes) + " from " + remoteClientName);
//		} else
		
//		System.out.println("Received  message " + message + " from "  + remoteClientName);
		if (stringMessage.contains(RESPONSE_KEYWORD))
			return;
		String response = groupSessionPort.getLocalName() + " " + RESPONSE_KEYWORD + " " + message;
		System.out.println("Broadcasting reponse " + response);
		groupSessionPort.sendAll(response);
//		System.out.println("Finished Broadcasting response " + response);
		
	}

}
