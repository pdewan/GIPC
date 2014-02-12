package variableserverport.datacomm.duplex;

import inputport.datacomm.duplex.DuplexClientInputPort;
import variableserverport.SimplexVariableServerClientPort;

public interface DuplexVariableServerClientPort<MessageType>  extends 
  SimplexVariableServerClientPort<MessageType>, // this will go
//DuplexInputPort<MessageType>
DuplexClientInputPort<MessageType>
	{

}
