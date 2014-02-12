package inputport.datacomm.simplex.buffer.nio;
public interface AcceptCommand extends RequestResponse{

	void addAcceptListener(SocketChannelAcceptListener aListener);	
//	public SocketChannelAcceptListener getListener();
}