package replicatedserverport.rpc.duplex.singleresponse;


import inputport.ConnectionType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.trace.TraceableBus;
import util.trace.Tracer;
import util.trace.port.ReplayStartInfo;
import util.trace.port.SyncReplayEndInfo;

// probbaly need a lock here or have an object that implements both kinds of trappers
public class AServerMessagesManager implements ServerMessagesManager{
	boolean replayMode = false;
	protected Map<String, String> clientToServer = new HashMap();
//	List<MessageWithId> messagesToFirstClient = new ArrayList();
//	Map<String, Integer> clientToCount = new HashMap();
	protected Map<String, List<MessageWithId>> clientToMessageBuffers = new HashMap();
	protected Map<String, Integer> clientToNumMessagesReceived = new HashMap(); // we  need separate buffers because server can crash after sending to some clients, also can send different number of messages to different clients
	protected Map<String, Integer> clientToNumMessagesGenerated = new HashMap(); // generated is better term as messages are not sent by the server necessarily

	BufferedMessageSender bufferedMessageSender;
	
	int maxMessagesGenerated; // maximum over all clients, can help us determine if we are the late-comer or the client. If the client is the late comer then it would have a smaller received value than the max we have generated.
	
//	boolean firstClientCreated; // determines if this is a late comer or not
	
	public AServerMessagesManager() {
		TraceableBus.addTraceableListener(this);
//		Tracer.setKeywordPrintStatus(this, true);
		
	}
	// called by send trapper
	public synchronized boolean shouldSend (String aServerName, String aClientName) {
		return aServerName.equals(clientToServer.get(aClientName)) ;

//				&& 		!isLatecomerAfterBuffering();
//				&& !isLatecomerBasedOnReplayMode();
		// no need to check replay mode as contrrol message is sent after replay mode
	}
	//called by receive trapper
    protected synchronized void updateServer(String aClientName, String aServerName) {
		Tracer.info(this, "Updated server of client " + aClientName + " to " + aServerName);
		clientToServer.put(aClientName, aServerName);
	}
	@Override
	public synchronized boolean isServerForAllClients(String myName) {
		Collection<String> servers = clientToServer.values();
		// there should be at least one entry, as it there was some activity in response
		// to which a message is being sent by the server
		for (String server:servers) {
			if (!myName.equals(server))
				return false;
		}
		return true;
		
		
	}
	// if we do not check for this  here and in addition to downstread latecomer connection manager
	// then we cannnot distinguish between client being latecomer or sever being latecomer
	// because in both cases the client got messages from non primary server
	// making sure control messages are not received out of order
	// by buffering new messages
	// control messages are now controlled so this should not be ncessary
	// we do want messages to be sent as this process may bring other processed up to speed
	public boolean isLatecomerBasedOnReplayMode() {
		return replayMode; 
//		return false;
	}
	
	// not a good test
	// TS > greater than other time stamps
	
	// called after a new message has been buffered -- not really, it is called before that
	// generated TS > received TSs
	// problem is it
	@Override
	public synchronized boolean isLatecomerAfterBuffering() {
		
		Set<String> clients = clientToNumMessagesGenerated.keySet();
		boolean atLeastOnePendingMessage = false; 
		for (String client: clients) {
			if (clientToNumMessagesGenerated.get(client) < clientToNumMessagesReceived.get(client)) {
				Tracer.info (this, " Is latecomer as number of messages generated for client " + 
							client + ": " + clientToNumMessagesGenerated.get(client) + " < " +
						" number of messages received:" + clientToNumMessagesReceived.get(client));
					return true;
			}
			if (clientToNumMessagesGenerated.get(client) >  clientToNumMessagesReceived.get(client))
				atLeastOnePendingMessage = true;
			
		}
		Tracer.info (this, " atLeastOnePendingMessage:" + atLeastOnePendingMessage);
		return !atLeastOnePendingMessage;		
		
	}
	
	// this does not really work for the case when a client sends to a empty list list of others
	// there is no other user to see if he has received all messages.
	// so we must figure out from the primary user
	// the primary user may become up to date before the secondary users
	// so we really need a more relaible, global way to figure out replay is over
	@Override
	public synchronized boolean isLatecomerBeforeBuffering() {
		
		Set<String> clients = clientToNumMessagesGenerated.keySet();
//		boolean atLeastOnePendingMessage = false; 
		for (String client: clients) {
			if (clientToNumMessagesGenerated.get(client) < clientToNumMessagesReceived.get(client)) {
				Tracer.info (this, " Is latecomer as number of messages generated for client " + 
						client + ": " + clientToNumMessagesGenerated.get(client) + " < " +
					" number of messages received:" + clientToNumMessagesReceived.get(client));
					return true;
			}
//			if (clientToNumMessagesGenerated.get(client) >  clientToNumMessagesReceived.get(client))
//				atLeastOnePendingMessage = true;
			
		}
		return false;		
		
	}
	// called by receive trapper
	 synchronized void updateNumMessagesReceived(String aClientName, int newVal) {
		Tracer.info(this, "Request to updated num received messasges of client " + aClientName + " to " + newVal);
		// may get a past control message
		int oldVal =  clientToNumMessagesReceived.get(aClientName); 
//		if (newVal < oldVal) {
//			Tracer.error("Ignoring   control message with  count for  " + aClientName + " = " + newVal + " < current count of:" + oldVal);
//			return;
//		}
		if (newVal <= oldVal) {
			Tracer.info(this, "Ignoring   control message with  count for  " + aClientName + " = " + newVal + " <= current count of:" + oldVal);
			return;
		}
//		if (newVal == 0) return;
		clientToNumMessagesReceived.put(aClientName, newVal); 
		Tracer.info(this, "Updated num received messasges of client " + aClientName + " to " + newVal);

		Integer sent = clientToNumMessagesGenerated.get(aClientName);
		
		if (sent == null ) {
			sent = 0;
			clientToNumMessagesGenerated.put(aClientName, sent);	
//			return;
		}
		// when client first joins it may have received messages from the latecomer so this value may not be zero
		// we have to distinguish between the server being a latecomer and the user being a latecomer
		// if client is latecomer, we update the generated count, otherwise we let the count be updated
		// because of the generation process
		if (sent < newVal && maxMessagesGenerated > newVal) { // client is the late comer
			// this can happen if the latecomer server sends messages on a servers behalf
			clientToNumMessagesGenerated.put(aClientName, newVal);
			if (sent != null)
			Tracer.info(this, "latecomer client joined after client " + aClientName + "  received:" + newVal + " messages");
		} else if (sent < newVal) {
			Tracer.info(this, "latecomer server joined after client " + aClientName + "  received:" + newVal + " messages");

		}
		// this is more inefficient than sender checking this but we want to clean
		// up even when no messages are sent
		cleanUpMessages(aClientName, newVal - 1); 
//		firstClientCreated = true;
	}
	@Override
	public synchronized int getNumberOfMessagesReceived(String aClientName) {
		return clientToNumMessagesReceived.get(aClientName);
	}

//	// called by receive trapper
//	public void updateCount(String aClientName, int newVal) {
//		Integer currentCount = clientToNumMessagesReceived.get(aClientName);
//		if (currentCount == null)
//			clientToNumMessagesReceived.put(aClientName, newVal);
//		else {
//			clientToNumMessagesReceived.put(aClientName, currentCount + newVal);
//		}
//		cleanUpMessages();
//	}
	// unused method, maybe at one time a global buffer was anticipated
	int computeMinCount () {
		Collection<Integer>  clientCounts = clientToNumMessagesReceived.values();
		int minCount = Integer.MAX_VALUE;
		for (Integer count: clientCounts) {
			minCount = Math.min(minCount, count);
		}
		return minCount;
	}
	protected int computeIndexOfId (List<MessageWithId> bufferedMessages, int id) {
		for (int i = 0; i < bufferedMessages.size(); i++) {
			MessageWithId message = bufferedMessages.get(i);
			if (message.getId() == id) {
				return i;
			}
		}
		return -1;
	}	
	protected synchronized void cleanUpMessages (String aClientName, int anId) {
		if (anId < 0) return;
		Tracer.info(this, " Cleaning up messages of " + aClientName + " up until id " + anId);
		List<MessageWithId> bufferedMessages = getAndMaybeCreateBufferedMessages(aClientName);
		int indexOfId = computeIndexOfId (bufferedMessages, anId);
		Tracer.info (this, "Index of id:" + anId);
//		for (int i = 0; i <= indexOfId; i++) {
//			bufferedMessages.remove(0);
//		}
		cleanUpMessages(bufferedMessages, indexOfId);
	}
	protected synchronized void cleanUpMessages (List<MessageWithId> bufferedMessages, int indexOfId) {
		if (indexOfId < 0) {
//			Tracer.info("Missing client message with id:" + indexOfId + " assuming client received them from a latecomer server.");
			bufferedMessages.clear();
			return;
		}
		Tracer.info(this, "Cleaning up buffered messages until index:" + indexOfId);
		if (indexOfId == bufferedMessages.size() - 1) {
			Tracer.info(this, "Clearing buffered messages " + bufferedMessages);
			bufferedMessages.clear();
			return;
		}
		for (int i = 0; i <= indexOfId; i++) {
//			Tracer.info(this, "Removing buffered messags at index" + i);
			bufferedMessages.remove(0);
		}		
	}
	//called by send trapper, which clears the buffer
	// what if we fail in the middle of this?
	// no problem as the process is dead so can be inconsistent
	public synchronized List<MessageWithId> getBufferedMessages(String aClientName) {
		return getAndMaybeCreateBufferedMessages(aClientName);
	}
	List<MessageWithId> getAndMaybeCreateBufferedMessages(String aClientName) {
		List<MessageWithId> retVal = clientToMessageBuffers.get(aClientName);
		if (retVal == null) { 
			retVal = new ArrayList();
			clientToMessageBuffers.put(aClientName, retVal);
		}
		return retVal;
	}

	// updated by send trapper
	// returns true if amesage actually added, that is not a latecomer
	// In latecomer mode
	public synchronized boolean addMessage(String aClientName, Object aMessage) {
		Tracer.info(this, "Add message: client:" + aClientName + " message: "
				+ aMessage);
		Integer numMessagesSent = clientToNumMessagesGenerated.get(aClientName);

		if (numMessagesSent == null) {
			numMessagesSent = 0;
		}
		boolean isLatecomer = isLatecomerBasedOnReplayMode();
		isLatecomer = false; // it should not matter is this is a latecomer or not
		// it is a latecomer, then no control messages would be processes until the most
		// recent one
		if (isLatecomer) {

			Tracer.info(this, "Did not add to buffer for client " + aClientName
					+ " message " + aMessage + " with id " + numMessagesSent
					+ " as process is latecomer");
			isLatecomer = true;
		} else {
			if (numMessagesSent < clientToNumMessagesReceived.get(aClientName)) {
				// client is a latecomer as we have established server is not,
				// so we need thist feature
				numMessagesSent = clientToNumMessagesReceived.get(aClientName);
			}
			MessageWithId messageWithId = new AMessageWithId(aMessage,
					numMessagesSent);
			List<MessageWithId> bufferedMessages = getAndMaybeCreateBufferedMessages(aClientName);
			bufferedMessages.add(messageWithId);
			Tracer.info(this, "Added to buffer for client " + aClientName
					+ " message " + aMessage + " with id " + numMessagesSent);
		}
		numMessagesSent++;
		maxMessagesGenerated = Math.max(maxMessagesGenerated, numMessagesSent);
		clientToNumMessagesGenerated.put(aClientName, numMessagesSent);
		Tracer.info(this, "num messages sent to client:"
				+ clientToNumMessagesGenerated.get(aClientName));

		return !isLatecomer;

	}

	// updated by send trapper
	public synchronized void incrementMessageCount(String aClientName) {
		Integer numMessagesSent = clientToNumMessagesGenerated.get(aClientName);
		if (numMessagesSent == null)
			numMessagesSent = 0;
		List<MessageWithId> bufferedMessages = getAndMaybeCreateBufferedMessages(aClientName);
		if (bufferedMessages.size() != 0) {
			Tracer.error("Internal errr: buffered messages:" + bufferedMessages
					+ " not empty when new message sent");
		}

		numMessagesSent++;
		maxMessagesGenerated = Math.max(maxMessagesGenerated, numMessagesSent);
		clientToNumMessagesGenerated.put(aClientName, numMessagesSent);
		Tracer.info(this, "maxMessagesGenerated for any client:"
				+ maxMessagesGenerated + " numeMessagesGenerated for "
				+ aClientName + ":" + numMessagesSent);
	}
	protected MessageWithId createMessageWithId(Object aMessage, int anId) {
		return new AMessageWithId(aMessage, anId);
		
	}
	
	@Override
	public synchronized BufferedMessageSender getBufferedMessageSender() {
		return bufferedMessageSender;
	}
	@Override
	public synchronized void setBufferedMessageSender(BufferedMessageSender newVal) {
		bufferedMessageSender = newVal;
		
	}
	@Override
	public void connected(String remoteEndName, ConnectionType connectionType) {
		if (connectionType == ConnectionType.CLIENT_TO_SESSION // session-based replication
				||	connectionType == ConnectionType.TO_CLIENT) // static replication
		{ 
//			Tracer.info(this, "Adding data structures for client:" + remoteEndName);
			
			clientToNumMessagesGenerated.put(remoteEndName, 0);
			clientToNumMessagesReceived.put(remoteEndName, 0);
			clientToMessageBuffers.put(remoteEndName, new ArrayList());
			Tracer.info(this, "Allocated data for client:" + remoteEndName );
		}
	}
	@Override
	public void notConnected(String remoteEndName, String anExplanation,
			ConnectionType connectionType) {
		
	}
	@Override
	public void disconnected(String remoteEndName,
			boolean anExplicitDsconnection, String anExplanation,
			ConnectionType connectionType) {
		if (connectionType == ConnectionType.CLIENT_TO_SESSION // session-based replication
				||	connectionType == ConnectionType.TO_CLIENT) // static replication
		{ 
			clientToNumMessagesGenerated.remove(remoteEndName);
			clientToNumMessagesReceived.remove(remoteEndName);
			clientToMessageBuffers.remove(remoteEndName);
			Tracer.info(this, "De-Allocated data for client:" + remoteEndName );

		}
		
	}
	@Override
	public synchronized void updateNumMessagesReceivedAndServer(String aClientName,
			String aServerName, int aNumMessages) {
		updateNumMessagesReceived(aClientName, aNumMessages);
		updateServer(aClientName, aServerName);
		
	}
	@Override
	public void newEvent(Exception aTraceable) {
			// actually should see if the message was really sent or not, and not just delayed or buffered - or should one?
			// if delayed, the toString message will also be delayed
//			if (aTraceable instanceof ByteBufferSendInfo || aTraceable instanceof ByteBufferReceiveInfo)
			if (aTraceable instanceof ReplayStartInfo ) {
				Tracer.info(this, "In replay mode");
				replayMode = true;
			} else if (aTraceable instanceof SyncReplayEndInfo) {
				// this does not work because the replay happens 
				// asynchronously
				Tracer.info(this, "Out of replay mode");

				replayMode = false; 
				
			} 
			
		}
		
	
	
	
}
