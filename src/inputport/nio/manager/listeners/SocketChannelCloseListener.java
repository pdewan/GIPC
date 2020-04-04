package inputport.nio.manager.listeners;

import java.nio.channels.SocketChannel;

public interface SocketChannelCloseListener {
	public void closed(SocketChannel aSocketChannel, Exception aReadException);
}
