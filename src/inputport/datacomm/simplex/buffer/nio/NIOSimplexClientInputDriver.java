package inputport.datacomm.simplex.buffer.nio;

import inputport.datacomm.simplex.buffer.SimplexBufferClientInputPortDriver;
import inputport.nio.manager.listeners.SocketChannelCloseListener;
import inputport.nio.manager.listeners.SocketChannelConnectListener;
import inputport.nio.manager.listeners.SocketChannelWriteListener;

import java.nio.channels.SocketChannel;

public interface NIOSimplexClientInputDriver extends SimplexBufferClientInputPortDriver<SocketChannel>, SocketChannelConnectListener, SocketChannelCloseListener, SocketChannelWriteListener{

}
