package inputport.nio.manager.factories;

import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.ReadCommand;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface ReadCommandFactory {	
	ReadCommand createReadCommand(SelectionManager aSelectionManager,
			SocketChannel aSocketChannel);	
	ReadCommand createReadCommand(SelectionManager aSelectionManager,
			SocketChannel aSocketChannel, Integer amInterestOps);	
}
