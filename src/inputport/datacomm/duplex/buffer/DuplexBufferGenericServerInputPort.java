package inputport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.duplex.DuplexServerInputPort;


// omitting <ChannelTyoe> in DuplexServerInputPort can cause probems
public interface DuplexBufferGenericServerInputPort<RequestChannelType, ChannelType>  extends
DuplexServerInputPortSkeleton<RequestChannelType, ChannelType> , DuplexServerInputPort<ByteBuffer>, DuplexBufferInputPort {

}
