package inputport.nio.manager;

import java.nio.channels.SocketChannel;

public interface WriteBoundedBufferListener {
	void writeBufferIsEmpty(SocketChannel aSocketChannel);

}
