package inputport.datacomm.duplex.buffer.nio;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import inputport.datacomm.duplex.buffer.DuplexServerInputPortDriver;
import inputport.datacomm.simplex.buffer.nio.NIOSimplexServerInputDriver;
import inputport.nio.manager.listeners.SocketChannelWriteListener;

public interface NIODuplexBufferServerInputPortDriver extends NIOSimplexServerInputDriver, DuplexServerInputPortDriver<ServerSocketChannel, SocketChannel>, SocketChannelWriteListener  {
//	 void send(SocketChannel socketChannel, ByteBuffer message);
}
