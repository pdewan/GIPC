package inputport.nio;

import java.nio.channels.SocketChannel;

public interface WriteBoundedBufferListener {
	void bufferIsEmpty(SocketChannel aSocketChannel);

}
