package port.old;

import inputport.nio.manager.SocketChannelCloseListener;
import inputport.nio.manager.SocketChannelReadListener;

public interface ClientProxy extends SocketChannelReadListener, SocketChannelCloseListener {

	String getClientName();

}
