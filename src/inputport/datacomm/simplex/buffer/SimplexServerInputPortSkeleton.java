package inputport.datacomm.simplex.buffer;


import inputport.DisconnectListener;



public interface SimplexServerInputPortSkeleton<RequestChannelType, MessageChannelType>  extends DisconnectListener, 
ChannelReceiveListener<MessageChannelType>{
	void setDriver(SimplexBufferServerInputPortDriver<RequestChannelType, MessageChannelType> theDriver);
	MessageChannelType getChannel(String clientName);
	String getClientName(MessageChannelType aChannel);

}
