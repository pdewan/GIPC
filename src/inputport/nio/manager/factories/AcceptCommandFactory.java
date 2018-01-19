package inputport.nio.manager.factories;

import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.AcceptCommand;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface AcceptCommandFactory {	
	AcceptCommand createAcceptCommand (
			SelectionManager aSelectingRunnable, ServerSocketChannel aSocketChannel);
	AcceptCommand createAcceptCommand (
			SelectionManager aSelectingRunnable, ServerSocketChannel aSocketChannel, 
			Integer anInterestOps);

}
