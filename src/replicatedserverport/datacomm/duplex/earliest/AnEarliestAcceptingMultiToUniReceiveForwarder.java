package replicatedserverport.datacomm.duplex.earliest;

import inputport.InputPort;
import inputport.datacomm.AnAbstractReceiveTrapper;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.simplex.SimplexClientInputPort;
import port.causal.AVectorTimeStamp;
import port.causal.VectorTimeStamp;
import util.trace.Tracer;


public class AnEarliestAcceptingMultiToUniReceiveForwarder<MessageType>  extends 
	AnAbstractReceiveTrapper<MessageType, MessageType> 
	implements ReceiveTrapper<MessageType, MessageType>{
	int messagesReceived = 0;
	VectorTimeStamp serversVectorTimeStamp = new AVectorTimeStamp();
	String logicalServerName;
	public AnEarliestAcceptingMultiToUniReceiveForwarder (InputPort anInputPort, ReceiveNotifier<MessageType> aDestination) {
		super (null, aDestination);
		logicalServerName = ((SimplexClientInputPort) anInputPort).getLogicalRemoteEndPoint();
	}


	@Override
	public void notifyPortReceive(String aSource, MessageType aMessage) {
		serversVectorTimeStamp.addMessage(aSource);
		int messagesReceivedFromCurrentServer = serversVectorTimeStamp.get(aSource);
		Tracer.info(this, "Received from " + aSource + "message#" + messagesReceivedFromCurrentServer + " message: " + aMessage);

		if (messagesReceivedFromCurrentServer > messagesReceived) {
			messagesReceived = messagesReceivedFromCurrentServer;
			Tracer.info(this, "Accepted message # " +  messagesReceived + " from " + aSource);
			destination.notifyPortReceive(logicalServerName, aMessage);
		} 
		else {
			Tracer.info(this,"Rejected duplicate message # " +  messagesReceivedFromCurrentServer + " from " + aSource);

		}
	}

}
