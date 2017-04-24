package port.old;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import replicatedserverport.rpc.duplex.singleresponse.AMessageWithId;
import replicatedserverport.rpc.duplex.singleresponse.AServerMessagesManager;
import replicatedserverport.rpc.duplex.singleresponse.MessageWithId;
import util.trace.Tracer;
// this will not work as not everyone gets the same set of messages, as we have others etc alloed.
//this is not session port
/**
 * @author dewan
 *
 */
// this class is not being used
public class AGroupServerMessagesManager extends AServerMessagesManager implements GroupServerMessagesManager{
//	Map<String, String> clientToServer = new HashMap();
//	Map<String, Integer> clientToCount = new HashMap();
//	Map<String, List<MessageWithId>> clientToMessageBuffers = new HashMap();
//	Map<String, Integer> clientToId = new HashMap();
	List<MessageWithId> bufferedMessages = new ArrayList();	
	int currentId = 0;
	String currentServerChoice;
	
//	public Map<String, String> getClientServers() {
//		return clientToServer;
//	}
//	public void setClientServers(Map<String, String> clientServers) {
//		this.clientToServer = clientServers;
//	}
//	public Map<String, Integer> getClientCounts() {
//		return clientToCount;
//	}
//	public void setClientCounts(Map<String, Integer> clientCounts) {
//		this.clientToCount = clientCounts;
//	}
//	public List<MessageWithId> getBufferedMessages() {
//		return bufferedMessages;
//	}
//	public void setBufferedMessages(List<MessageWithId> bufferedMessages) {
//		this.bufferedMessages = bufferedMessages;
//	}	
	// called by send trapper
	@Override
	public boolean shouldSend (String aServerName, Collection<String> clientNames) {
		return aServerName.equals(currentServerChoice);
	}
	//called by receive trapper
	@Override
	protected void updateServer(String aClientName, String aServerName) {
		Tracer.info("Server updated by client " + aClientName + " to " + aServerName);
		super.updateServer(aClientName, aServerName);
		currentServerChoice = aServerName;
	}
	
	// called by receive trapper
//	public void updateCount(String aClientName, int newVal) {
//		clientToId.put(aClientName, newVal);
//		cleanUpMessages(aClientName, newVal);
//	}

////	 called by receive trapper
//	public void updateCount(String aClientName, int newVal) {
//		Integer currentCount = clientToCount.get(aClientName);
//		if (currentCount == null)
//			clientToCount.put(aClientName, newVal);
//		else {
//			clientToCount.put(aClientName, currentCount + newVal);
//		}
//		cleanUpMessages();
//	}
	protected int computeMinId () {
		Collection<Integer>  clientCounts = clientToNumMessagesReceived.values();
		int minCount = Integer.MAX_VALUE;
		for (Integer count: clientCounts) {
			minCount = Math.min(minCount, count);
		}		
		return minCount;
	}
	@Override
	protected int computeIndexOfId (List<MessageWithId> bufferedMessages, int id) {
		for (int i = 0; i < bufferedMessages.size(); i++) {
			MessageWithId message = bufferedMessages.get(i);
			if (message.getId() >= id) {
				return i;
			}
		}
		return -1;
	}
	// parameters of method not being used
	@Override
	protected void cleanUpMessages (String aClientName, int aCount) {
		Tracer.info(this,"Client  " +  aClientName + " cleanup of " + aCount + " messages");
	
		int minId = computeMinId();
		
		int indexOfId = computeIndexOfId (bufferedMessages, minId - 1);
		for (int i = 0; i <= indexOfId; i++) {
			bufferedMessages.remove(0);
		}		
	}
//	//called by send trapper, which clears the buffer
//	public List<MessageWithId> getBufferedMessages(String aClientName) {
//		return bufferedMessages;
//	}
//	List<MessageWithId> getAndMaybeCreateBufferedMessages(String aClientName) {		
//		return bufferedMessages;
//	}
	
	//called by group trapper, which clears the buffer
	@Override
	public List<MessageWithId> getBufferedMessages(Collection<String> clientNames) {
		return bufferedMessages;
	}
	
	
	
	// updated by send trapper
//	public void addMessage(String aClientName, Object aMessage) {
//		Integer currentId = clientToId.get(aClientName);
//		if (currentId == null)
//			currentId = 0;		
//		MessageWithId messageWithId = new AMessageWithId(aMessage, currentId);
//		List<MessageWithId> bufferedMessages = getAndMaybeCreateBufferedMessages(aClientName);
//		bufferedMessages.add(messageWithId);
//		currentId ++;
//		clientToId.put(aClientName, currentId);
//	}
	
	// updated by send trapper
	@Override
	public void addMessage(Collection<String> clientNames, Object aMessage) {			
		MessageWithId messageWithId = new AMessageWithId(aMessage, currentId);
		bufferedMessages.add(messageWithId);
		currentId ++;
	}

}
