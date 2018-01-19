package inputport.nio.manager.listeners;

import java.nio.channels.SocketChannel;

public interface WriteBoundedBufferListener {
	void writeBufferIsEmpty(SocketChannel aSocketChannel);

}
