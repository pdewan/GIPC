package replicatedserverport.rpc.duplex.singleresponse;

import util.trace.TraceableListener;
import inputport.datacomm.SendTrapper;

public interface ClientMessagesManager extends TraceableListener {
	
	boolean addServer(String server);
	boolean removeServer(String server) ;
	void receivedNewMessage(String aRemoteEnd, Object aMessage);
	int getReceivedMessages();
	boolean sendUpdateControlMessage() ;
	void controlMessageSent();
	SendTrapper<Object, Object> getSendTrapper();
	void setSendTrapper (SendTrapper<Object, Object> newVal);
	public String getCurrentServer();

}
