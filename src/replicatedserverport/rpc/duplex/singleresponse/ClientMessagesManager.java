package replicatedserverport.rpc.duplex.singleresponse;

import inputport.datacomm.SendTrapper;
import util.trace.TraceableListener;

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
