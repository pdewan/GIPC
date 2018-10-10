package inputport.datacomm.simplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.simplex.SimplexServerInputPort;

public interface GenericSimplexServerInputPort<OpenChannelType, ChannelType> extends SimplexServerInputPort<ByteBuffer>, SimplexServerInputPortSkeleton<OpenChannelType, ChannelType>{

}
