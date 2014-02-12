package replicatedserverport.rpc.duplex.singleresponse;

import inputport.ConnectionListener;

import java.io.Serializable;
import java.util.List;

import util.trace.TraceableListener;

public interface ServerMessagesManager extends Serializable, ConnectionListener, TraceableListener {
//	Map<String, String> getClientServers();
//	void setClientServers(Map<String, String> clientServers);
//	Map<String, Integer> getClientCounts();
//	void setClientCounts(Map<String, Integer> clientCounts) ;
//	List<MessageWithId> getBufferedMessages();
//	void setBufferedMessages(List<MessageWithId> bufferedMessages);
	
	// called by send trapper
	boolean shouldSend (String aServerName, String aClientName) ;
	//called by receive trapper
//	void updateServer(String aClientName, String aServerName);
	// called by receive trapper
//	public void updateNumMessagesReceived(String aClientName, int newVal);
	// must do this atomically as otherwise sever can can get updated without num messages
	// which may cause process to not think it is a latecomer
	// tension between software engineering separation and messy algorithm design
	public void updateNumMessagesReceivedAndServer(String aClientName, String aServer, int aNumMessages);
	// called by send filterer
	public boolean addMessage(String aClientName, Object aMessage);
	// called by send filterer, which also clears buffer afterwards
	public List<MessageWithId> getBufferedMessages(String aClientName);
	BufferedMessageSender getBufferedMessageSender();
	void setBufferedMessageSender (BufferedMessageSender newVal);
	int getNumberOfMessagesReceived(String aClientName);
	 void incrementMessageCount(String aClientName);
	 boolean isServerForAllClients(String myName);
	boolean isLatecomerAfterBuffering();
	boolean isLatecomerBeforeBuffering();
	boolean isLatecomerBasedOnReplayMode();





}
