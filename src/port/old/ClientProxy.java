package port.old;

import inputport.datacomm.simplex.buffer.nio.SocketChannelCloseListener;
import inputport.datacomm.simplex.buffer.nio.SocketChannelReadListener;

public interface ClientProxy extends SocketChannelReadListener, SocketChannelCloseListener {

	String getClientName();

}
