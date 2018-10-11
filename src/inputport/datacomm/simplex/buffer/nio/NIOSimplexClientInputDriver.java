package inputport.datacomm.simplex.buffer.nio;

import java.nio.channels.SocketChannel;

import inputport.datacomm.simplex.buffer.SimplexBufferClientInputPortDriver;
import inputport.nio.manager.listeners.SocketChannelCloseListener;
import inputport.nio.manager.listeners.SocketChannelConnectListener;
import inputport.nio.manager.listeners.SocketChannelWriteListener;

public interface NIOSimplexClientInputDriver extends SimplexBufferClientInputPortDriver<SocketChannel>, SocketChannelConnectListener, SocketChannelCloseListener, SocketChannelWriteListener{

}
