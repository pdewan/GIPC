package inputport.nio.manager.listeners;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface SocketChannelReadListener {
	void socketChannelRead(SocketChannel aSocketChannel, ByteBuffer aMessage, int aLength);
//	void socketChannelClosed(SocketChannel theSocketChannel, IOException theReadException);
}
