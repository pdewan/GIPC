package port.sessionserver.relay.mvc.example;

import port.sessionserver.ServerPortDescription;
import inputport.ConnectionListener;
import inputport.ConnectionListenerWithPort;
import inputport.InputPort;

public interface ServerConnectingConnectionListener extends ConnectionListenerWithPort{
	public void initNextServerPortDescription(ServerPortDescription aServerPortDescription) ;
	public void initNextConnectionListener(ConnectionListenerWithPort aConnectionListener);
}
