package inputport.nio.manager;

import java.nio.channels.SocketChannel;

public interface ReadCommand extends RequestResponse {
	public void addReadListener(SocketChannelReadListener theReadListener);
	public void addCloseListener(SocketChannelCloseListener theCloseListener);
	public void notifyCloseListeners(SocketChannel theSocketChannel, Exception anException);

}
