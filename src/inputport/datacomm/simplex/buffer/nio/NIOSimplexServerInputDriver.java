package inputport.datacomm.simplex.buffer.nio;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import inputport.datacomm.simplex.buffer.SimplexBufferServerInputPortDriver;
import inputport.nio.manager.listeners.SocketChannelAcceptListener;
import inputport.nio.manager.listeners.SocketChannelCloseListener;
import inputport.nio.manager.listeners.SocketChannelConnectListener;
import inputport.nio.manager.listeners.SocketChannelReadListener;
import inputport.nio.manager.listeners.WriteBoundedBufferListener;

public interface NIOSimplexServerInputDriver extends WriteBoundedBufferListener, SimplexBufferServerInputPortDriver<ServerSocketChannel, SocketChannel>, SocketChannelAcceptListener, SocketChannelConnectListener, SocketChannelReadListener, SocketChannelCloseListener {

}
