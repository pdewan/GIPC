package inputport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.duplex.DuplexClientInputPort;




public interface DuplexBufferGenericClientInputPort<ChannelType> extends DuplexClientInputPortSkeleton<ChannelType>, 
DuplexClientInputPort<ByteBuffer>,
DuplexBufferInputPort{


}
