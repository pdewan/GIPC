package inputport.datacomm.simplex.buffer.nio;

import inputport.datacomm.simplex.buffer.SimplexBufferServerInputPortDriver;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface NIOSimplexServerInputDriver extends SimplexBufferServerInputPortDriver<ServerSocketChannel, SocketChannel>, SocketChannelAcceptListener, SocketChannelConnectListener, SocketChannelReadListener, SocketChannelCloseListener {

}
