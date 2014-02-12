package inputport.datacomm.simplex.buffer;

import inputport.datacomm.simplex.SimplexServerInputPort;

import java.nio.ByteBuffer;

public interface GenericSimplexServerInputPort<OpenChannelType, ChannelType> extends SimplexServerInputPort<ByteBuffer>, SimplexServerInputPortSkeleton<OpenChannelType, ChannelType>{

}
