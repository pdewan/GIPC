package inputport.nio.manager.commands;

import inputport.nio.manager.listeners.SocketChannelAcceptListener;

public interface AcceptCommand extends RequestResponse{

	void addAcceptListener(SocketChannelAcceptListener aListener);	
//	public SocketChannelAcceptListener getListener();
}