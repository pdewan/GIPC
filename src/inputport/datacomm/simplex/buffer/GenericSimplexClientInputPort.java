package inputport.datacomm.simplex.buffer;

import inputport.datacomm.simplex.SimplexClientInputPort;

import java.nio.ByteBuffer;

public interface GenericSimplexClientInputPort<ChannelType> extends SimplexClientInputPort<ByteBuffer>, 
	SimplexClientInputPortSkeleton<ChannelType> {

}
