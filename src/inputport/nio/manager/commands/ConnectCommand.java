package inputport.nio.manager.commands;
import java.net.InetAddress;

import inputport.nio.manager.listeners.SocketChannelConnectListener;
public interface ConnectCommand extends RequestResponse{
	public InetAddress getServerHost();
	public int getPort();
//	public SocketChannelConnectListener getListener();
	void addConnectListener(SocketChannelConnectListener aListener);
}