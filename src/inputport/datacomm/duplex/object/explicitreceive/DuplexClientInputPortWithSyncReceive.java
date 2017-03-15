package inputport.datacomm.duplex.object.explicitreceive;

import inputport.datacomm.duplex.DuplexClientInputPort;

public interface DuplexClientInputPortWithSyncReceive<MessageType> extends 
	DuplexClientInputPort<MessageType>, ExplicitReceive<MessageType>, ExplicitSourceReceive<MessageType>
{
}
