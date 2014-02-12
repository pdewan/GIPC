package replicatedserverport.rpc.duplex.singleresponse;

import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public class ADownstreamSingleResponseClientDuplexRelayedCallReceiveTrapper extends AnAbstractSingleResponseClientDuplexCallReceiveTrapper  {
//		SimplexClientInputPort clientInputPort;
////		ReplicatedServerDuplexClientPort clientInputPort;
//		ClientMessagesManager clientMessagesManager;

	public ADownstreamSingleResponseClientDuplexRelayedCallReceiveTrapper(InputPort anInputPort, 
				ReceiveNotifier<Object> destination,
				ClientMessagesManager aClientMessagesManager) {
		super(anInputPort, destination, aClientMessagesManager);
//		clientInputPort.addConnectionListener(this);

		// no need to connect
	}
	@Override
	public  synchronized void notifyPortReceive(String remoteEnd, Object message) {
		super.notifyPortReceive(remoteEnd, message);
		if (message instanceof MessageWithSource) // do not deal with non relayed messages from session server
		processReceivedMessage(remoteEnd, message);
	}
	
//	
//	@Override
//	public  synchronized void notifyPortReceive(String remoteEnd, Object message) {
//		
//		destination.notifyPortReceive(remoteEnd, message);
//		if (message instanceof MessageWithSource) // messages from late comer need not be processed
//			processReceivedMessage(remoteEnd, message);
//	}
	
	

}
