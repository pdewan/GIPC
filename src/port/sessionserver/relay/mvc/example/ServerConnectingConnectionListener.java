package port.sessionserver.relay.mvc.example;

import inputport.ConnectionListenerWithPort;
import port.sessionserver.ServerPortDescription;

public interface ServerConnectingConnectionListener extends ConnectionListenerWithPort{
	public void initNextServerPortDescription(ServerPortDescription aServerPortDescription) ;
	public void initNextConnectionListener(ConnectionListenerWithPort aConnectionListener);
}
