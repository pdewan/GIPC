package inputport.nio.manager.commands;

import inputport.nio.manager.listeners.SocketChannelCloseListener;
import inputport.nio.manager.listeners.SocketChannelReadListener;

import java.nio.channels.SocketChannel;

public interface ReadCommand extends RequestResponse {
	public void addReadListener(SocketChannelReadListener theReadListener);
	public void addCloseListener(SocketChannelCloseListener theCloseListener);
	public void notifyCloseListeners(SocketChannel theSocketChannel, Exception anException);

}
