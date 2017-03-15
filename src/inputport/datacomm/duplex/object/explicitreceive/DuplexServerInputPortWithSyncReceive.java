package inputport.datacomm.duplex.object.explicitreceive;

import inputport.datacomm.duplex.DuplexServerInputPort;

public interface DuplexServerInputPortWithSyncReceive<MessageType> extends 
	DuplexServerInputPort<MessageType>, ExplicitReceive<MessageType>, ExplicitSourceReceive<MessageType> {
}
