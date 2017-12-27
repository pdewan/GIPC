package inputport.datacomm.simplex.buffer.nio;

import inputport.datacomm.simplex.buffer.SimplexBufferClientInputPortDriver;
import inputport.nio.SocketChannelCloseListener;
import inputport.nio.SocketChannelConnectListener;
import inputport.nio.SocketChannelWriteListener;

import java.nio.channels.SocketChannel;

public interface NIOSimplexClientInputDriver extends SimplexBufferClientInputPortDriver<SocketChannel>, SocketChannelConnectListener, SocketChannelCloseListener, SocketChannelWriteListener{

}
