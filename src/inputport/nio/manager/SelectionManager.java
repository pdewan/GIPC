package inputport.nio.manager;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.nio.manager.commands.ReadCommand;
import inputport.nio.manager.commands.RequestResponse;
import inputport.nio.manager.commands.WriteCommand;
import inputport.nio.manager.listeners.SocketChannelCloseListener;
import inputport.nio.manager.listeners.SocketChannelReadListener;

public interface SelectionManager extends Runnable {	
	public void putBufferedWrite (WriteCommand bufferedWrite);
	public void putRequestResponse (RequestResponse request);
//	public void putResponse(SelectableChannel theChannel,
//			Response theResponse);
//
//	public Response getResponse(SelectableChannel theChannel);
	public Selector getSelector();
	public void registerReadListener(SocketChannel theChannel,
			SocketChannelReadListener theReadListener);
	public void registerCloseListener(SelectableChannel theChannel,
			SocketChannelCloseListener theCloseListener);
	public void close(SelectionKey selectionKey, Exception readException);
	public ReadCommand getReadHandler(SocketChannel theSocketChannel);
	WriteBoundedBuffer getWriteBoundedBuffer(SocketChannel aSocketChannel);


//	boolean registerWriteOpsForBufferedWrites(SocketChannel channel);
}