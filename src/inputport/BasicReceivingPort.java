package inputport;

import inputport.datacomm.ReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveTrapperSetter;
import inputport.datacomm.simplex.SenderQueryable;


public interface BasicReceivingPort<MessageType> extends
	ReceiveRegistrarAndNotifier<MessageType>, 
	ReceiveTrapperSetter<MessageType, MessageType>,
	SenderQueryable 
	{
 }
