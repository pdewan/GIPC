package inputport.datacomm.duplex.buffer;

import inputport.datacomm.ReceiveListener;
import inputport.datacomm.simplex.buffer.SimplexClientInputPortSkeleton;

import java.nio.ByteBuffer;

public interface DuplexClientInputPortSkeleton<ChannelType> extends SimplexClientInputPortSkeleton<ChannelType>, ReceiveListener<ByteBuffer> {
//	void setDriver(DuplexClientInputPortDriver<ChannelType> theChannelImplementation);

}
