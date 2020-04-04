package inputport.nio.manager.listeners;

import java.nio.channels.SocketChannel;

public interface SocketChannelConnectListener {
	public void connected(SocketChannel aSocketChannel);	
	public void notConnected(SocketChannel aSocketChannel, Exception e);
}
