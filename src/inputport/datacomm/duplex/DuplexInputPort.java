package inputport.datacomm.duplex;

import inputport.BasicReceivingPort;
import inputport.BasicSendingPort;
import inputport.InputPort;
import inputport.RemoteEndPointProperties;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.duplex.object.explicitreceive.ExplicitReceive;
import inputport.datacomm.duplex.object.explicitreceive.ExplicitSourceReceive;

public interface DuplexInputPort<MessageType> extends 
		InputPort,
		BasicSendingPort<MessageType>,
//		ReceiveListener<MessageType>,
		BasicReceivingPort<MessageType>,
		ExplicitReceive<MessageType>,
		ExplicitSourceReceive<MessageType>,
		DuplexSender<MessageType>,
		RemoteEndPointProperties // adding this for now at least

{


}
