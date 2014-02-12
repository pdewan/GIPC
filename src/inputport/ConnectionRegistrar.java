package inputport;
//methods common to both parties of a connection and names ensure we can use this in a session
public interface ConnectionRegistrar  {	
	// in a socket implementation, accept at the server or connect at client is a connect
	void  addConnectionListener (ConnectionListener portConnectListener);
	void  removeConnectionListener (ConnectionListener portConnectListener);


}
