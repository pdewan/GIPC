package replicatedserverport.datacomm.duplex;

import inputport.InputPort;
import inputport.datacomm.AnAbstractReceiveTrapper;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.datacomm.simplex.SenderQueryable;
import inputport.datacomm.simplex.SimplexClientInputPort;
import util.trace.Tracer;


public class AnAllAcceptingMultiToReplicatedReceiveTrapper<MessageType>  extends 
	AnAbstractReceiveTrapper<MessageType, MessageType> 
	implements ReceiveTrapper<MessageType, MessageType>{
//	int messagesReceived = 0;
//	VectorTimeStamp serversVectorTimeStamp = new AVectorTimeStamp();
	String logicalServerName;
//	LastSenderQueryable lastSenderQueryable;
	public AnAllAcceptingMultiToReplicatedReceiveTrapper (InputPort anInputPort, 
			ReceiveNotifier<MessageType> aDestination) {
//			LastSenderQueryable aLastSenderQueryable) {
		super (anInputPort, aDestination);
		logicalServerName = ((SimplexClientInputPort) anInputPort).getLogicalRemoteEndPoint();
//		lastSenderQueryable = aLastSenderQueryable;
	}


	@Override
	public void notifyPortReceive(String aSource, MessageType aMessage) {
//		serversVectorTimeStamp.addMessage(aSource);
//		int messagesReceivedFromCurrentServer = serversVectorTimeStamp.get(aSource);
		Tracer.info(this, "Received from " + aSource + " message: " + aMessage);
//		if (!aSource.equals(logicalServerName)) { // no one else has translated also
////			((DuplexInputPort) inputPort).setLastSender(aSource); // for reply messages, but this is set also by other objects after this set
//			lastSenderQueryable.setLastSender(aSource);
//		}
//
//		if (messagesReceivedFromCurrentServer > messagesReceived) {
//			messagesReceived = messagesReceivedFromCurrentServer;
//			Tracer.info("Accepted message # " +  messagesReceived + " from " + aSource);
			destination.notifyPortReceive(logicalServerName, aMessage);
//		} 
	}

}
