package inputport.nio;
public interface AcceptCommand extends RequestResponse{

	void addAcceptListener(SocketChannelAcceptListener aListener);	
//	public SocketChannelAcceptListener getListener();
}