package port.old;

import inputport.nio.manager.SocketChannelAcceptListener;
import inputport.nio.manager.SocketChannelCloseListener;
import inputport.nio.manager.SocketChannelConnectListener;
import inputport.nio.manager.SocketChannelReadListener;

public interface MonolithicNIOServerInputPort extends MonolithicServerInputPort, SocketChannelAcceptListener, SocketChannelConnectListener, SocketChannelReadListener, SocketChannelCloseListener {

}
