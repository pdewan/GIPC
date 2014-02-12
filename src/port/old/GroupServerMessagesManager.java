package port.old;

import java.util.List;
import java.util.Set;

import replicatedserverport.rpc.duplex.singleresponse.MessageWithId;
import replicatedserverport.rpc.duplex.singleresponse.ServerMessagesManager;

public interface GroupServerMessagesManager extends ServerMessagesManager{
	List<MessageWithId> getBufferedMessages(Set<String> clientNames);
	void addMessage(Set<String> clientNames, Object message);
	boolean shouldSend(String serverName, Set<String> clientNames);
}
