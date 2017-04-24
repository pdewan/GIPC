package port.old;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import replicatedserverport.rpc.duplex.singleresponse.MessageWithId;
import replicatedserverport.rpc.duplex.singleresponse.ServerMessagesManager;

public interface GroupServerMessagesManager extends ServerMessagesManager{
	List<MessageWithId> getBufferedMessages(Collection<String> clientNames);
	void addMessage(Collection<String> clientNames, Object message);
	boolean shouldSend(String serverName, Collection<String> clientNames);
}
