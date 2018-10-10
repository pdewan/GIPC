package inputport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.ReceiveListener;
import inputport.datacomm.simplex.buffer.SimplexClientInputPortSkeleton;

public interface DuplexClientInputPortSkeleton<ChannelType> extends SimplexClientInputPortSkeleton<ChannelType>, ReceiveListener<ByteBuffer> {
//	void setDriver(DuplexClientInputPortDriver<ChannelType> theChannelImplementation);

}
