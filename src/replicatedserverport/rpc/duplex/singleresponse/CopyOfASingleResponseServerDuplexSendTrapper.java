package replicatedserverport.rpc.duplex.singleresponse;

import inputport.InputPort;
import inputport.datacomm.ASendMessageForwarder;
import inputport.datacomm.NamingSender;

import java.util.List;

import util.trace.Tracer;


public class CopyOfASingleResponseServerDuplexSendTrapper extends ASendMessageForwarder<Object> 
	implements BufferedMessageSender{
	InputPort inputPort;
	ServerMessagesManager sentMessagesManager;
	public CopyOfASingleResponseServerDuplexSendTrapper(InputPort anInputPort, NamingSender<Object> destination, ServerMessagesManager aSentMessagesManager) {
		super(destination);
		inputPort = anInputPort;
		sentMessagesManager = aSentMessagesManager;
	}
	
	

	@Override
	public synchronized void send(String aClientName, Object aMessage) {
		sentMessagesManager.addMessage(aClientName, aMessage);
		if (sentMessagesManager.shouldSend(inputPort.getLocalName(), aClientName)) {
			List<MessageWithId> bufferedMessages = sentMessagesManager.getBufferedMessages(aClientName); // should never have > 1 message
			for (int i = 0; i < bufferedMessages.size(); i++) {
				MessageWithId messageWithID = bufferedMessages.get(i);
				Tracer.info(this, "sending buffered message " +  messageWithID.getMessage());
				destination.send (aClientName, messageWithID.getMessage());
			}
			
			bufferedMessages.clear();
		}		
	}



	@Override
	public void sendBufferedMessage(String clientName, Object message) {
		destination.send(clientName, message);
	}
  

}
