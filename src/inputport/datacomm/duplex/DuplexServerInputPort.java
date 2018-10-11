package inputport.datacomm.duplex;

import inputport.datacomm.simplex.SimplexServerInputPort;

public interface DuplexServerInputPort<MessageType> extends 
	SimplexServerInputPort<MessageType>, DuplexInputPort<MessageType>
//	ExplicitReceive<MessageType>, ExplicitSourceReceive<MessageType> 
//	DuplexSender<MessageType>, 
//	ConnectionsQueryable
//	DuplexServerQueryable
//	QueryableDuplexServerSender<MessageType>
//	ServerQueries
	/*SocketChannelWriteListener,*/   {
	//public String[] getConnectionNames();
//	String getLastSender();	
//	void setLastSender(String newVal);

}
