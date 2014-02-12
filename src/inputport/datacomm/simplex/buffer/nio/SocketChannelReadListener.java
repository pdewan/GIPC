package inputport.datacomm.simplex.buffer.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface SocketChannelReadListener {
	void socketChannelRead(SocketChannel theSocketChannel, ByteBuffer theMessage);
//	void socketChannelClosed(SocketChannel theSocketChannel, IOException theReadException);
}
