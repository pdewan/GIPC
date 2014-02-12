package replicatedserverport.rpc.duplex.singleresponse;

import inputport.InputPort;
import inputport.datacomm.ASendMessageForwarder;
import inputport.datacomm.NamingSender;

import java.util.List;

import util.trace.Tracer;


public class ASingleResponseServerDuplexSendTrapper extends ASendMessageForwarder<Object> 
	implements BufferedMessageSender{
	InputPort inputPort;
	ServerMessagesManager sentMessagesManager;
	public ASingleResponseServerDuplexSendTrapper(InputPort anInputPort, NamingSender<Object> destination, ServerMessagesManager aSentMessagesManager) {
		super(destination);
		inputPort = anInputPort;
		sentMessagesManager = aSentMessagesManager;
	}
	
	

	@Override
	public synchronized void send(String aClientName, Object aMessage) {
//		boolean isLateComer = sentMessagesManager.isLatecomerAfterBuffering();
		boolean isLatecomer = !sentMessagesManager.addMessage(aClientName, aMessage); 
		if (isLatecomer) {
			Tracer.info(this, "Latecomer Not sending: "  + aMessage+ " to " +  aClientName + " ");
			return;
		}
		// should never have > 1 message
//		Tracer.info(this, "buffered message: "  + aMessage+ " for " +  aClientName);
//		if (!isLateComer)
		ASingleResponseServerDuplexSendTrapper.sendBufferedMessages(aClientName,  inputPort, sentMessagesManager);
//		if (sentMessagesManager.shouldSend(inputPort.getLocalName(), aClientName)) {
//			List<MessageWithId> bufferedMessages = sentMessagesManager.getBufferedMessages(aClientName); // should never have > 1 message
//			for (int i = 0; i < bufferedMessages.size(); i++) {
//				MessageWithId messageWithID = bufferedMessages.get(i);
//				Tracer.info(this, "sending buffered message " +  messageWithID.getMessage());
//				destination.send (aClientName, messageWithID.getMessage());
//			}
//			
//			bufferedMessages.clear();
//		}		
	}



	@Override
	public void sendBufferedMessage(String clientName, Object message) {
		destination.send(clientName, message);
	}



	public static void sendBufferedMessages(String remoteEnd, InputPort inputPort, ServerMessagesManager serverSentMessagesManager) {
			// this is perhaps an optimization if we latcomer connection manager prevents back calls
			if (!(serverSentMessagesManager.shouldSend(inputPort.getLocalName(), remoteEnd))) {
				Tracer.info(serverSentMessagesManager, "Non primary server not sending buffered message");
				return;
			}
			List<MessageWithId> bufferedMessages = serverSentMessagesManager.getBufferedMessages(remoteEnd);
	//		if (bufferedMessages.size() == 0) return;
	//		int firstQueuedId = bufferedMessages.get(0).getId();
	//		int startIndex = serverSentMessagesManager.getNumberOfMessagesReceived(remoteEnd) + 1 - firstQueuedId;
	//		 update count makes the startIndex computation redundant
			for (int i = 0; i < bufferedMessages.size(); i++) {
				MessageWithId messageWithID = bufferedMessages.get(i);
//				Tracer.info(sender, "sending buffered message " +  messageWithID.getMessage());
				Tracer.info(serverSentMessagesManager, "Sending buffered message:" + messageWithID.getMessage() + " to " + remoteEnd);

				serverSentMessagesManager.getBufferedMessageSender().sendBufferedMessage (remoteEnd, messageWithID.getMessage());
			}
//			Tracer.info("clearing buffer for " + remoteEnd);
			bufferedMessages.clear();
		}
  

}
