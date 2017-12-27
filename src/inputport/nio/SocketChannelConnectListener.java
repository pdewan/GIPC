package inputport.nio;

import java.nio.channels.SocketChannel;

public interface SocketChannelConnectListener {
	public void connected(SocketChannel theSocketChannel);	
	public void notConnected(SocketChannel theSocketChannel, Exception e);
}
