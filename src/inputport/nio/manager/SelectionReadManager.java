package inputport.nio.manager;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import inputport.nio.manager.commands.ReadCommand;
import inputport.nio.manager.listeners.SocketChannelReadListener;

public interface SelectionReadManager {

//	public abstract ReadCommand createReadHandler(SocketChannel theSocketChannel);

//	public abstract void allocateReadState(SocketChannel theSocketChannel);

	public abstract ReadCommand getReadHandler(SocketChannel theSocketChannel);

	public abstract void registerReadListener(SocketChannel theChannel,
			SocketChannelReadListener theReadListener);

	boolean processRead(SelectionKey theSelectionKey);

}