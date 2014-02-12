package inputport.datacomm.duplex;

import inputport.BasicReceivingPort;
import inputport.BasicSendingPort;
import inputport.InputPort;
import inputport.RemoteEndPointProperties;

public interface DuplexInputPort<MessageType> extends 
		InputPort,
		BasicSendingPort<MessageType>,
		BasicReceivingPort<MessageType>,
		DuplexSender<MessageType>,
		RemoteEndPointProperties // adding this for now at least

{


}
