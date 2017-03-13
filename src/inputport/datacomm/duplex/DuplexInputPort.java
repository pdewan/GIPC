package inputport.datacomm.duplex;

import inputport.BasicReceivingPort;
import inputport.BasicSendingPort;
import inputport.InputPort;
import inputport.RemoteEndPointProperties;
import inputport.datacomm.ReceiveListener;

public interface DuplexInputPort<MessageType> extends 
		InputPort,
		BasicSendingPort<MessageType>,
//		ReceiveListener<MessageType>,
		BasicReceivingPort<MessageType>,
		DuplexSender<MessageType>,
		RemoteEndPointProperties // adding this for now at least

{


}
