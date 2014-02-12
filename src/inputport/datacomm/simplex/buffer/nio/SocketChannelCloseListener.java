package inputport.datacomm.simplex.buffer.nio;

import java.nio.channels.SocketChannel;

public interface SocketChannelCloseListener {
	//public void socketChannelRead(SocketChannel theSocketChannel, ByteBuffer theMessage);
	public void closed(SocketChannel theSocketChannel, Exception theReadException);
}
