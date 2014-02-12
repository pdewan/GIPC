package inputport.datacomm.simplex.buffer;

import inputport.ConnectionListener;

public interface SimplexClientInputPortSkeleton<MessageChannelType> extends  /*SendNotifier, ConnectionNotifier,*/ 
		ConnectionListener, ByteBufferSendListener
  {
	void setDriver(SimplexBufferClientInputPortDriver<MessageChannelType> aChannelImplementation);
}
