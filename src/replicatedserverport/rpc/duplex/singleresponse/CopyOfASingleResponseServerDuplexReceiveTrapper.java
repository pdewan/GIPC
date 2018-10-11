package replicatedserverport.rpc.duplex.singleresponse;

import java.util.List;

import inputport.InputPort;
import inputport.datacomm.AnAbstractReceiveTrapper;
import inputport.datacomm.ReceiveNotifier;
import util.trace.Tracer;


public class CopyOfASingleResponseServerDuplexReceiveTrapper extends AnAbstractReceiveTrapper<Object, Object> {
	ServerMessagesManager serverSentMessagesManager;
	InputPort inputPort;
	public CopyOfASingleResponseServerDuplexReceiveTrapper(InputPort anInputPort, 
			ReceiveNotifier<Object> destination, 
			ServerMessagesManager aServerSentMessagesManager) {
		super(null, destination);
		serverSentMessagesManager = aServerSentMessagesManager;
//		duplexInputPort = (DuplexInputPort) anInputPort;
		inputPort = anInputPort;
	}

	@Override
	public synchronized void notifyPortReceive(String remoteEnd, Object message) {
		if (message instanceof ControlMessage) {
			Tracer.info(this, "Received control message " + message + " from " + remoteEnd);
			ControlMessage controlMessage = (ControlMessage) message;

			processControlMessage(remoteEnd, controlMessage, serverSentMessagesManager);
//			ControlMessage controlMessage = (ControlMessage) message;
//			Tracer.info(this, "Received control message " + message + " from " + remoteEnd);
//			serverSentMessagesManager.updateServer(remoteEnd, controlMessage.getServerChoice());
//			serverSentMessagesManager.updateCount(remoteEnd, controlMessage.getServerMessageCount());
//			if (!(serverSentMessagesManager.shouldSend(inputPort.getLocalName(), remoteEnd))) return;
			sendBufferedMessages(remoteEnd, controlMessage.getServerMessageCount(), inputPort, serverSentMessagesManager);
//			List<MessageWithId> bufferedMessages = serverSentMessagesManager.getBufferedMessages(remoteEnd);
//			for (int i = 0; i < bufferedMessages.size(); i++) {
//				MessageWithId messageWithID = bufferedMessages.get(i);
//				Tracer.info(this, "sending buffered message " +  messageWithID.getMessage());
//				serverSentMessagesManager.getBufferedMessageSender().sendBufferedMessage (remoteEnd, messageWithID.getMessage());
//			}
//			Tracer.info(this, "clearing buffer for " + remoteEnd);
//			bufferedMessages.clear();
			
		} else {
			Tracer.info(this, "Received non control message " + message + " from " + remoteEnd);
			destination.notifyPortReceive(remoteEnd, message);
		}
	}
	protected static void sendBufferedMessages(String remoteEnd, int numberOfMessagesReceived, InputPort inputPort, ServerMessagesManager serverSentMessagesManager) {
		if (!(serverSentMessagesManager.shouldSend(inputPort.getLocalName(), remoteEnd))) return;

		List<MessageWithId> bufferedMessages = serverSentMessagesManager.getBufferedMessages(remoteEnd);
		if (bufferedMessages.size() == 0) return;
		int firstQueuedId = bufferedMessages.get(0).getId();
		int startIndex = numberOfMessagesReceived - firstQueuedId;
		for (int i = startIndex; i < bufferedMessages.size(); i++) {
			MessageWithId messageWithID = bufferedMessages.get(i);
			if (messageWithID.getId() <  numberOfMessagesReceived)
			Tracer.info("sending buffered message " +  messageWithID.getMessage());
			serverSentMessagesManager.getBufferedMessageSender().sendBufferedMessage (remoteEnd, messageWithID.getMessage());
		}
		Tracer.info("clearing buffer for " + remoteEnd);
		bufferedMessages.clear();
	}
	public static  void processControlMessage (String remoteEnd, ControlMessage controlMessage, ServerMessagesManager serverSentMessagesManager) {
//		serverSentMessagesManager.updateServer(remoteEnd, controlMessage.getServerChoice());
//		serverSentMessagesManager.updateNumMessagesReceived(remoteEnd, controlMessage.getServerMessageCount());
	}

}
