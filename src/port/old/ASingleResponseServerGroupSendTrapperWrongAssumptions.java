package port.old;

import java.util.Collection;
import java.util.List;

import inputport.InputPort;
import inputport.datacomm.group.AnAbstractGroupSendTrapper;
import inputport.datacomm.group.GroupNamingSender;
import replicatedserverport.rpc.duplex.singleresponse.MessageWithId;

public class ASingleResponseServerGroupSendTrapperWrongAssumptions extends AnAbstractGroupSendTrapper<Object, Object> {
	InputPort inputPort;
	GroupServerMessagesManager sentMessagesManager;
	public ASingleResponseServerGroupSendTrapperWrongAssumptions(InputPort anInputPort, GroupNamingSender<Object> destination, GroupServerMessagesManager aSentMessagesManager) {
		super(destination);
		inputPort = anInputPort;
		sentMessagesManager = aSentMessagesManager;	
	}

	
	@Override
	public void send(Collection<String> clientNames, Object aMessage) {
		sentMessagesManager.addMessage(clientNames, aMessage);
		if (sentMessagesManager.shouldSend(inputPort.getLocalName(), clientNames)) {
			List<MessageWithId> bufferedMessages = sentMessagesManager.getBufferedMessages(clientNames);
			for (int i = 0; i < bufferedMessages.size(); i++) {
				MessageWithId messageWithID = bufferedMessages.get(i);
				destination.send (clientNames, messageWithID.getMessage());
			}
			bufferedMessages.clear();
		}		
	} 

}
