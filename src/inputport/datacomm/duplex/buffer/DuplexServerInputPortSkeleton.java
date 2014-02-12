package inputport.datacomm.duplex.buffer;

import inputport.datacomm.simplex.buffer.ByteBufferSendListener;
import inputport.datacomm.simplex.buffer.SimplexServerInputPortSkeleton;

public interface DuplexServerInputPortSkeleton<RequestChannelType, MessageChannelType> extends SimplexServerInputPortSkeleton<RequestChannelType, MessageChannelType>, ByteBufferSendListener{
	void setDriver(DuplexServerInputPortDriver<RequestChannelType, MessageChannelType> theDriver);
	MessageChannelType getChannel(String aRemoteEnd);
	String getClientName(MessageChannelType aChannel);
}
