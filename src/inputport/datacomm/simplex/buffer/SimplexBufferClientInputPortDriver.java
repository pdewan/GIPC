package inputport.datacomm.simplex.buffer;

import inputport.ConnectionManager;
import inputport.datacomm.NamingSender;

import java.nio.ByteBuffer;


//import old.UniNamingSender;

public interface SimplexBufferClientInputPortDriver<ChannelType> extends ConnectionManager,
NamingSender<ByteBuffer> 
/*InputPortDriver<ChannelType>*/  {


    void setSkeleton(SimplexClientInputPortSkeleton<ChannelType> theSkeleton);
    SimplexClientInputPortSkeleton<ChannelType> getSkeleton();


}
