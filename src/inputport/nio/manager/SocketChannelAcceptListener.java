package inputport.nio.manager;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface SocketChannelAcceptListener {
	public void socketChannelAccepted(ServerSocketChannel aServerSocketChannel, SocketChannel aSocketChannel);	
}
