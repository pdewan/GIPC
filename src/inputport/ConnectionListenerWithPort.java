package inputport;

import port.sessionserver.ServerPortDescription;

public interface ConnectionListenerWithPort extends ConnectionListener{
	void initInputPort(InputPort anInputPort);
	
}
