package inputport.nio.manager.commands;

import inputport.nio.manager.listeners.SocketChannelWriteListener;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

//not an extension of RequestResponse because request is made for the whole bounded buffer, not an individual one
public interface WriteCommand extends Response{
//	public boolean respond();
	public abstract ByteBuffer getWriteBuffer();
	public void setSocketChannel(SocketChannel theSocketChannel);
	public SocketChannel getSocketChannel();
	public int getId();
	void addwriteListener(SocketChannelWriteListener aListener);
//	void register(SocketChannelCloseListener aListener);

}