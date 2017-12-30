package inputport.datacomm.simplex.buffer.nio;

import inputport.datacomm.simplex.buffer.SimplexBufferServerInputPortDriver;
import inputport.nio.manager.SocketChannelAcceptListener;
import inputport.nio.manager.SocketChannelCloseListener;
import inputport.nio.manager.SocketChannelConnectListener;
import inputport.nio.manager.SocketChannelReadListener;
import inputport.nio.manager.WriteBoundedBufferListener;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface NIOSimplexServerInputDriver extends WriteBoundedBufferListener, SimplexBufferServerInputPortDriver<ServerSocketChannel, SocketChannel>, SocketChannelAcceptListener, SocketChannelConnectListener, SocketChannelReadListener, SocketChannelCloseListener {

}
