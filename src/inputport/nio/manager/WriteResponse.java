package inputport.nio.manager;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface WriteResponse extends Response {

	public SocketChannel getSocketChannel();

	public ByteBuffer getWriteBuffer();

}