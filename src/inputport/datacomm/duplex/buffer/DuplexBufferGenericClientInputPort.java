package inputport.datacomm.duplex.buffer;

import inputport.datacomm.duplex.DuplexClientInputPort;

import java.nio.ByteBuffer;




public interface DuplexBufferGenericClientInputPort<ChannelType> extends DuplexClientInputPortSkeleton<ChannelType>, 
DuplexClientInputPort<ByteBuffer>,
DuplexBufferInputPort{


}
