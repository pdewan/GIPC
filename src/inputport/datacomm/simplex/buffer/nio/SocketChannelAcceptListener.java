package inputport.datacomm.simplex.buffer.nio;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface SocketChannelAcceptListener {
	public void socketChannelAccepted(ServerSocketChannel theServerSocketChannel, SocketChannel theSocketChannel);	
}
