package inputport.nio.manager;

import java.nio.channels.SocketChannel;

public interface SocketChannelCloseListener {
	//public void socketChannelRead(SocketChannel theSocketChannel, ByteBuffer theMessage);
	public void closed(SocketChannel theSocketChannel, Exception theReadException);
}
