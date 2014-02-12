package inputport.datacomm.duplex.buffer;

import inputport.datacomm.simplex.buffer.SimplexBufferClientInputPortDriver;

public interface DuplexClientInputPortDriver<ChannelType> extends SimplexBufferClientInputPortDriver<ChannelType> 
//SocketChannelReadListener, SocketCloseListener  
{
//	void startChannel();
    void setSkeleton(DuplexBufferGenericClientInputPort<ChannelType> theSkeleton);
    
}
