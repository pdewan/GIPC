package replicatedserverport.rpc.duplex.singleresponse;

import inputport.InputPort;
import inputport.datacomm.AnAbstractReceiveTrapper;
import inputport.datacomm.ReceiveNotifier;
import util.trace.Tracer;


public class ASingleResponseServerDuplexReceiveTrapper extends AnAbstractReceiveTrapper<Object, Object> {
	protected ServerMessagesManager serverSentMessagesManager;
	protected InputPort inputPort;
	public ASingleResponseServerDuplexReceiveTrapper(InputPort anInputPort, 
			ReceiveNotifier<Object> destination, 
			ServerMessagesManager aServerSentMessagesManager) {
		super(null, destination);
		serverSentMessagesManager = aServerSentMessagesManager;
//		duplexInputPort = (DuplexInputPort) anInputPort;
		inputPort = anInputPort;
	}
	public void sendBufferedMessages(String remoteEnd, InputPort inputPort, ServerMessagesManager serverSentMessagesManager) {
		ASingleResponseServerDuplexSendTrapper.sendBufferedMessages(remoteEnd, inputPort, serverSentMessagesManager);

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
			Tracer.info(this, "sending buffered message for " +  remoteEnd);
//			if (!serverSentMessagesManager.isLatecomer())
			
//			ASingleResponseServerDuplexSendTrapper.sendBufferedMessages(remoteEnd, inputPort, serverSentMessagesManager);

			sendBufferedMessages(remoteEnd, inputPort, serverSentMessagesManager);

			
			
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
	public static  void processControlMessage (String remoteEnd, ControlMessage controlMessage, ServerMessagesManager serverSentMessagesManager) {
		// these two steps should occur atomically
//		serverSentMessagesManager.updateServer(remoteEnd, controlMessage.getServerChoice());
//		serverSentMessagesManager.updateNumMessagesReceived(remoteEnd, controlMessage.getServerMessageCount());
		serverSentMessagesManager.updateNumMessagesReceivedAndServer(remoteEnd, 
				controlMessage.getServerChoice(),
										controlMessage.getServerMessageCount());

	}

}
