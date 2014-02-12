package inputport;
import inputport.datacomm.SendTrapperSetter;
import inputport.datacomm.simplex.buffer.SendRegistrarAndNotifier;
public interface BasicSendingPort<MessageType> extends 
					InputPort,
					SendRegistrarAndNotifier,
					SendTrapperSetter<MessageType, MessageType>
//					ConnectionRegistrarAndNotifier, 
//					ConnectionsQueryable
//					RemoteEndPointProperties
{
}
