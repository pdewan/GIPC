package inputport.datacomm.simplex.buffer.nio;

import inputport.datacomm.simplex.buffer.SimplexBufferServerInputPortDriver;
import inputport.nio.SocketChannelAcceptListener;
import inputport.nio.SocketChannelCloseListener;
import inputport.nio.SocketChannelConnectListener;
import inputport.nio.SocketChannelReadListener;
import inputport.nio.WriteBoundedBufferListener;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface NIOSimplexServerInputDriver extends WriteBoundedBufferListener, SimplexBufferServerInputPortDriver<ServerSocketChannel, SocketChannel>, SocketChannelAcceptListener, SocketChannelConnectListener, SocketChannelReadListener, SocketChannelCloseListener {

}
