package extraip;

import inputport.InputPort;
import inputport.datacomm.AnAbstractReceiveTrapper;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.simplex.SimplexClientInputPort;
import port.causal.AVectorTimeStamp;
import port.causal.VectorTimeStamp;

public class AMultiToUniReceiveForwarder<MessageType>  extends 
	AnAbstractReceiveTrapper<MessageType, MessageType> 
	implements ReceiveTrapper<MessageType, MessageType>{
	int messagesReceived = 0;
	VectorTimeStamp serversVectorTimeStamp = new AVectorTimeStamp();
	String logicalServerName;
	public AMultiToUniReceiveForwarder (InputPort anInputPort, ReceiveNotifier<MessageType> aDestination) {
		super (null, aDestination);
		logicalServerName = ((SimplexClientInputPort) anInputPort).getLogicalRemoteEndPoint();
	}


	@Override
	public void notifyPortReceive(String aSource, MessageType aMessage) {
		serversVectorTimeStamp.addMessage(aSource);
		int messagesReceivedFromCurrentServer = serversVectorTimeStamp.get(aSource);
		if (messagesReceivedFromCurrentServer > messagesReceived) {
			messagesReceived = messagesReceivedFromCurrentServer;
			destination.notifyPortReceive(logicalServerName, aMessage);
		}
	}

}
