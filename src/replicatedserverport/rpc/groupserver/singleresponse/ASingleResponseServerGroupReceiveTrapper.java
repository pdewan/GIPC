package replicatedserverport.rpc.groupserver.singleresponse;

import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;
import replicatedserverport.rpc.duplex.singleresponse.ASingleResponseServerDuplexReceiveTrapper;
import replicatedserverport.rpc.duplex.singleresponse.ASingleResponseServerDuplexSendTrapper;
import replicatedserverport.rpc.duplex.singleresponse.ControlMessage;
import replicatedserverport.rpc.duplex.singleresponse.ServerMessagesManager;
import util.trace.Tracer;


public class ASingleResponseServerGroupReceiveTrapper 
		extends ASingleResponseServerDuplexReceiveTrapper {
//	GroupServerMessagesManager serverSentMessagesManager;
//	InputPort inputPort;
	public ASingleResponseServerGroupReceiveTrapper(InputPort anInputPort, 
			ReceiveNotifier<Object> destination, 
			GroupServerMessagesManager aServerSentMessagesManager) {
		super(anInputPort, destination, aServerSentMessagesManager);
	}
	
	GroupServerMessagesManager groupServerMessagesManager() {
		return (GroupServerMessagesManager) serverSentMessagesManager;
	}

//	@Override
//	public synchronized void notifyPortReceive(String remoteEnd, Object message) {
//		if (message instanceof ControlMessage) {
//			Tracer.info(this, "Received control message " + message + " from " + remoteEnd);
//			ControlMessage controlMessage = (ControlMessage) message;
//
//			processControlMessage(remoteEnd, controlMessage, serverSentMessagesManager);
////			ControlMessage controlMessage = (ControlMessage) message;
////			Tracer.info(this, "Received control message " + message + " from " + remoteEnd);
////			serverSentMessagesManager.updateServer(remoteEnd, controlMessage.getServerChoice());
////			serverSentMessagesManager.updateCount(remoteEnd, controlMessage.getServerMessageCount());
////			if (!(serverSentMessagesManager.shouldSend(inputPort.getLocalName(), remoteEnd))) return;
//			Tracer.info(this, "sending buffered message for " +  remoteEnd);
////			if (!serverSentMessagesManager.isLatecomer())
//			ASingleResponseServerDuplexSendTrapper.sendBufferedMessages(remoteEnd, inputPort, serverSentMessagesManager);
////			List<MessageWithId> bufferedMessages = serverSentMessagesManager.getBufferedMessages(remoteEnd);
////			for (int i = 0; i < bufferedMessages.size(); i++) {
////				MessageWithId messageWithID = bufferedMessages.get(i);
////				Tracer.info(this, "sending buffered message " +  messageWithID.getMessage());
////				serverSentMessagesManager.getBufferedMessageSender().sendBufferedMessage (remoteEnd, messageWithID.getMessage());
////			}
////			Tracer.info(this, "clearing buffer for " + remoteEnd);
////			bufferedMessages.clear();
//			
//		} else {
//			Tracer.info(this, "Received non control message " + message + " from " + remoteEnd);
//			destination.notifyPortReceive(remoteEnd, message);
//		}
//	}


}
