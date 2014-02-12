package port.old;

import inputport.InputPort;
import inputport.datacomm.group.AnAbstractGroupSendTrapper;
import inputport.datacomm.group.GroupNamingSender;

import java.util.List;
import java.util.Set;


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
	public void send(Set<String> clientNames, Object aMessage) {
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
