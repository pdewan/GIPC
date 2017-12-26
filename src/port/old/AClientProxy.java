package port.old;
import inputport.ConnectionType;

import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
public class AClientProxy  implements ClientProxy  {
	String clientName;
	ConnectionSendReceiptNotifier listenablePort;
	public AClientProxy (ConnectionSendReceiptNotifier theListenablePort) {
		listenablePort = theListenablePort;
	}
	@Override
	public String getClientName() {
		return clientName;
	}
	@Override
	public void socketChannelRead(SocketChannel theSocketChannel,
			ByteBuffer message, int aLength) {	
		int length = message.limit() - message.position();
		if (clientName == null) {
//			byte[] stringBytes = new byte[message.limit()];
			byte[] stringBytes = new byte[length];
			//message.rewind();
//			for (int i = 0; i < message.position(); i++)
//				stringBytes[i] = message.get(i);
			message.get(stringBytes);
			//message.get(stringBytes, 0, message.position() -1 );
			clientName = new String(stringBytes);
			listenablePort.notifyConnect(clientName, ConnectionType.TO_SERVER);
		} else {
			listenablePort.notifyPortReceive(clientName, message, length);			
		}		
	}
	@Override
	public void closed(SocketChannel theSocketChannel,
			Exception theReadException) {
		if (clientName != null) {
			listenablePort.notifyDisconnect(clientName, theReadException instanceof EOFException, theReadException.getMessage(), null);
		}
		
	}
	
}
