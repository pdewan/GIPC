package port.old;

import inputport.ConnectionsQueryable;
import inputport.datacomm.duplex.DuplexSender;
import inputport.datacomm.simplex.buffer.SendRegistrarAndNotifier;

import java.nio.ByteBuffer;






public interface MonolithicDuplexServerInputPort extends MonolithicServerInputPort,  ConnectionsQueryable, /*SocketChannelWriteListener,*/  DuplexSender<ByteBuffer>, SendRegistrarAndNotifier {
	//public String[] getConnectionNames();
	String getLastSender();	
	void setLastSender(String newVal);

}
