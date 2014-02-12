package inputport;
public interface ClientInputPort<MessageType> extends 
					BasicSendingPort<MessageType>,
//					InputPort,
//					SendRegistrarAndNotifier,
//					ConnectionRegistrarAndNotifier, 
//					ConnectionsQueryable,
					RemoteEndPointProperties
{
}
