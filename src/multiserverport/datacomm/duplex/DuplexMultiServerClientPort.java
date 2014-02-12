package multiserverport.datacomm.duplex;

import inputport.datacomm.duplex.DuplexClientInputPort;
import multiserverport.datacomm.simplex.SimplexMultiServerClientPort;

public interface DuplexMultiServerClientPort<MessageType> extends 
//	DuplexBroadcastPort<MessageType>, 
	DuplexClientInputPort<MessageType>,
	SimplexMultiServerClientPort<MessageType>{
//  does not have serverId fromServerInputPort
//	DuplexServerInputPort<MessageType>{

}
