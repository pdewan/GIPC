package port.old;

import inputport.datacomm.simplex.buffer.nio.SocketChannelCloseListener;
import inputport.datacomm.simplex.buffer.nio.SocketChannelConnectListener;
import inputport.datacomm.simplex.buffer.nio.SocketChannelWriteListener;

public interface MonolithicNIOClientInputPort extends MonolithicClientInputPort, SocketChannelConnectListener, SocketChannelCloseListener, SocketChannelWriteListener{

}
