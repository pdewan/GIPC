package inputport.nio.manager;
import java.net.InetAddress;
public interface ConnectCommand extends RequestResponse{
	public InetAddress getServerHost();
	public int getPort();
//	public SocketChannelConnectListener getListener();
	void addConnectListener(SocketChannelConnectListener aListener);
}