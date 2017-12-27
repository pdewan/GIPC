package port.old;

import inputport.nio.SocketChannelAcceptListener;
import inputport.nio.SocketChannelCloseListener;
import inputport.nio.SocketChannelConnectListener;
import inputport.nio.SocketChannelReadListener;

public interface MonolithicNIOServerInputPort extends MonolithicServerInputPort, SocketChannelAcceptListener, SocketChannelConnectListener, SocketChannelReadListener, SocketChannelCloseListener {

}
