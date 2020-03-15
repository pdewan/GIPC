package inputport.datacomm.simplex;
import inputport.BasicReceivingPort;
import inputport.RemoteEndPointProperties;
import inputport.ServerInputPort;
public interface SimplexServerInputPort<MessageType> extends 
				ServerInputPort, BasicReceivingPort<MessageType>,
				// adding this because a server port becomes 
				RemoteEndPointProperties 
				//  a session port which needs to have a logical end point
				
//				ReceiveRegistrarAndNotifier<MessageType>, 
//				ReceiveTrapperSetter<MessageType, MessageType>, LastSenderQueryable
{
//	String getLastSender();	
//	void setLastSender(String newVal);

}
