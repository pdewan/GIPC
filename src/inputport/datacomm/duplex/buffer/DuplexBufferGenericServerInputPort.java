package inputport.datacomm.duplex.buffer;

import inputport.datacomm.duplex.DuplexServerInputPort;

import java.nio.ByteBuffer;


// omitting <ChannelTyoe> in DuplexServerInputPort can cause probems
public interface DuplexBufferGenericServerInputPort<RequestChannelType, ChannelType>  extends DuplexServerInputPortSkeleton<RequestChannelType, ChannelType> , DuplexServerInputPort<ByteBuffer> {

}
