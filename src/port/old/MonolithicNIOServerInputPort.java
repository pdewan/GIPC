package port.old;

import inputport.datacomm.simplex.buffer.nio.SocketChannelAcceptListener;
import inputport.datacomm.simplex.buffer.nio.SocketChannelCloseListener;
import inputport.datacomm.simplex.buffer.nio.SocketChannelConnectListener;
import inputport.datacomm.simplex.buffer.nio.SocketChannelReadListener;

public interface MonolithicNIOServerInputPort extends MonolithicServerInputPort, SocketChannelAcceptListener, SocketChannelConnectListener, SocketChannelReadListener, SocketChannelCloseListener {

}
