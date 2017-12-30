package inputport.datacomm.simplex.buffer.nio;

import inputport.datacomm.simplex.buffer.SimplexBufferClientInputPortDriver;
import inputport.nio.manager.SocketChannelCloseListener;
import inputport.nio.manager.SocketChannelConnectListener;
import inputport.nio.manager.SocketChannelWriteListener;

import java.nio.channels.SocketChannel;

public interface NIOSimplexClientInputDriver extends SimplexBufferClientInputPortDriver<SocketChannel>, SocketChannelConnectListener, SocketChannelCloseListener, SocketChannelWriteListener{

}
