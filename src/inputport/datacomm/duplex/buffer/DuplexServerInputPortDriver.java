package inputport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.NamingSender;
import inputport.datacomm.simplex.buffer.SimplexBufferServerInputPortDriver;

public interface DuplexServerInputPortDriver<RequestChannelType, ChannelType> extends 
SimplexBufferServerInputPortDriver<RequestChannelType, ChannelType>, NamingSender<ByteBuffer> {
    void setSkeleton(DuplexServerInputPortSkeleton<RequestChannelType, ChannelType> theSkeleton);
//	void send(ChannelType channel, ByteBuffer message);		
//	void send(String destination, ByteBuffer message);			




}
