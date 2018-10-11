package inputport.datacomm.duplex.buffer.nio;

import java.nio.channels.SocketChannel;

import inputport.datacomm.duplex.buffer.DuplexClientInputPortDriver;
import inputport.datacomm.simplex.buffer.nio.NIOSimplexClientInputDriver;
import inputport.nio.manager.listeners.SocketChannelReadListener;
import inputport.nio.manager.listeners.WriteBoundedBufferListener;


public interface NIODuplexBufferClientInputPortDriver extends 
DuplexClientInputPortDriver<SocketChannel>, 
NIOSimplexClientInputDriver, 
SocketChannelReadListener,
WriteBoundedBufferListener{
	

}
