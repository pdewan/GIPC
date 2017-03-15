package inputport.datacomm.duplex;

import inputport.datacomm.duplex.object.explicitreceive.ExplicitReceive;
import inputport.datacomm.duplex.object.explicitreceive.ExplicitSourceReceive;
import inputport.datacomm.simplex.SimplexClientInputPort;



public interface DuplexClientInputPort<MessageType> 
extends SimplexClientInputPort<MessageType>,  DuplexInputPort<MessageType>,
ExplicitReceive<MessageType>, ExplicitSourceReceive<MessageType>{
	
}
