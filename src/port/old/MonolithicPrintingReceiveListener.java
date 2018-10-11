package port.old;

import java.nio.ByteBuffer;

import extraip.ASendConnectionListener;
import inputport.InputPort;


public class MonolithicPrintingReceiveListener extends ASendConnectionListener implements ByteBufferReceiveListener {

public MonolithicPrintingReceiveListener(InputPort anInputPort) {
		super(anInputPort);
		// TODO Auto-generated constructor stub
	}

	//	@Override
//	public void messageReceived(String clientName, ByteBuffer message, int length) {
//		int msgSize = message.limit();
//		byte[] msgBytes = new byte[msgSize];
//		message.get(msgBytes);
//		System.out.println("received message:" + new String(msgBytes) + " from " + clientName);
//		//return null;
//	}
	@Override
	public void messageReceived(String clientName, ByteBuffer message, int length) {
		byte[] msgBytes = new byte[length];
		message.get(msgBytes);
		System.out.println("received message:" + new String(msgBytes) + " from " + clientName);
		//return null;
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

}
