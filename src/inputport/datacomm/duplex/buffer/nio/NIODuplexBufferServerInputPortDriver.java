package inputport.datacomm.duplex.buffer.nio;

import inputport.datacomm.duplex.buffer.DuplexServerInputPortDriver;
import inputport.datacomm.simplex.buffer.nio.NIOSimplexServerInputDriver;
import inputport.nio.SocketChannelWriteListener;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface NIODuplexBufferServerInputPortDriver extends NIOSimplexServerInputDriver, DuplexServerInputPortDriver<ServerSocketChannel, SocketChannel>, SocketChannelWriteListener  {
//	 void send(SocketChannel socketChannel, ByteBuffer message);
}
