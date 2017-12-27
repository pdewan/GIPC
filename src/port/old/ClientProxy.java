package port.old;

import inputport.nio.SocketChannelCloseListener;
import inputport.nio.SocketChannelReadListener;

public interface ClientProxy extends SocketChannelReadListener, SocketChannelCloseListener {

	String getClientName();

}
