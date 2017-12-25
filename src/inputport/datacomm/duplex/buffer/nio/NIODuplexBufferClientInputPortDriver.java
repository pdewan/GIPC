package inputport.datacomm.duplex.buffer.nio;

import inputport.datacomm.duplex.buffer.DuplexClientInputPortDriver;
import inputport.datacomm.simplex.buffer.nio.NIOSimplexClientInputDriver;
import inputport.datacomm.simplex.buffer.nio.SocketChannelReadListener;
import inputport.datacomm.simplex.buffer.nio.WriteBoundedBufferListener;

import java.nio.channels.SocketChannel;


public interface NIODuplexBufferClientInputPortDriver extends 
DuplexClientInputPortDriver<SocketChannel>, 
NIOSimplexClientInputDriver, 
SocketChannelReadListener,
WriteBoundedBufferListener{
	

}
