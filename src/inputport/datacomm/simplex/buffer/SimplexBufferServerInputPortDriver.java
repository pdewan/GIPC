package inputport.datacomm.simplex.buffer;

import inputport.ConnectionManager;

public interface SimplexBufferServerInputPortDriver<RequestChannelType, MessageChannelType> extends ConnectionManager {
    void setSkeleton(SimplexServerInputPortSkeleton<RequestChannelType, MessageChannelType> aSkeleton);
    void disconnect(MessageChannelType aChannel);

}
