package port.old;

import java.nio.ByteBuffer;

import inputport.ConnectionsQueryable;
import inputport.datacomm.duplex.DuplexSender;
import inputport.datacomm.simplex.buffer.SendRegistrarAndNotifier;






public interface MonolithicDuplexServerInputPort extends MonolithicServerInputPort,  ConnectionsQueryable, /*SocketChannelWriteListener,*/  DuplexSender<ByteBuffer>, SendRegistrarAndNotifier {
	//public String[] getConnectionNames();
	String getLastSender();	
	void setLastSender(String newVal);

}
