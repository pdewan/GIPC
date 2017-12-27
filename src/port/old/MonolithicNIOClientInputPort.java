package port.old;

import inputport.nio.SocketChannelCloseListener;
import inputport.nio.SocketChannelConnectListener;
import inputport.nio.SocketChannelWriteListener;

public interface MonolithicNIOClientInputPort extends MonolithicClientInputPort, SocketChannelConnectListener, SocketChannelCloseListener, SocketChannelWriteListener{

}
