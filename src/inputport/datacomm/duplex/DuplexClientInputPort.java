package inputport.datacomm.duplex;

import inputport.datacomm.simplex.SimplexClientInputPort;



public interface DuplexClientInputPort<MessageType> 
extends SimplexClientInputPort<MessageType>,  DuplexInputPort<MessageType>
//ExplicitReceive<MessageType>, ExplicitSourceReceive<MessageType>
{
	
}
