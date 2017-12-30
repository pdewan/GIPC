package inputport.nio.manager;
public interface AcceptCommand extends RequestResponse{

	void addAcceptListener(SocketChannelAcceptListener aListener);	
//	public SocketChannelAcceptListener getListener();
}