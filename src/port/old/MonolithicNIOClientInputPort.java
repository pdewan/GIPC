package port.old;

import inputport.nio.manager.SocketChannelCloseListener;
import inputport.nio.manager.SocketChannelConnectListener;
import inputport.nio.manager.SocketChannelWriteListener;

public interface MonolithicNIOClientInputPort extends MonolithicClientInputPort, SocketChannelConnectListener, SocketChannelCloseListener, SocketChannelWriteListener{

}
