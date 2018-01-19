package port.old;

import inputport.nio.manager.listeners.SocketChannelCloseListener;
import inputport.nio.manager.listeners.SocketChannelReadListener;

public interface ClientProxy extends SocketChannelReadListener, SocketChannelCloseListener {

	String getClientName();

}
