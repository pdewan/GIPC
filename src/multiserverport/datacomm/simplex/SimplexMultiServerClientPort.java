package multiserverport.datacomm.simplex;

import inputport.BasicSendingPort;
import inputport.datacomm.Sender;
import inputport.datacomm.simplex.SimplexClientInputPort;

public interface SimplexMultiServerClientPort<MessageType> extends /*BroadcastPort<MessageType>, */
	SimplexClientInputPort<MessageType>
//	Sender<MessageType>,
//	BasicSendingPort<MessageType>
	{

}
