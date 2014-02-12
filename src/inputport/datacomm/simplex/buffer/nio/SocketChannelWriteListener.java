package inputport.datacomm.simplex.buffer.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface SocketChannelWriteListener {
	public void written(SocketChannel socketChannel, ByteBuffer theWriteBuffer, int sendId);	
}
