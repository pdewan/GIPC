package inputport.datacomm.simplex;

import inputport.ClientInputPort;
import inputport.datacomm.Sender;
import multiserverport.datacomm.simplex.SimplexMultiServerClientPort;

public interface SimplexClientInputPort<MessageType> extends ClientInputPort<MessageType>,
		Sender<MessageType>
//		SendRegistrarAndNotifier,
//		ConnectionRegistrarAndNotifier, 
//		RemoteEndPointProperties,
//		SendTrapperSetter<MessageType, MessageType>
{
}
