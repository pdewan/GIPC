package inputport.datacomm.simplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.simplex.SimplexClientInputPort;

public interface GenericSimplexClientInputPort<ChannelType> extends SimplexClientInputPort<ByteBuffer>, 
	SimplexClientInputPortSkeleton<ChannelType> {

}
