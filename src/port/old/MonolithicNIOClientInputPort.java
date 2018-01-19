package port.old;

import inputport.nio.manager.listeners.SocketChannelCloseListener;
import inputport.nio.manager.listeners.SocketChannelConnectListener;
import inputport.nio.manager.listeners.SocketChannelWriteListener;

public interface MonolithicNIOClientInputPort extends MonolithicClientInputPort, SocketChannelConnectListener, SocketChannelCloseListener, SocketChannelWriteListener{

}
