package inputport.nio.manager.factories;

import java.nio.channels.SocketChannel;

import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.ReadCommand;

public interface ReadCommandFactory {	
	ReadCommand createReadCommand(SelectionManager aSelectionManager,
			SocketChannel aSocketChannel);	
	ReadCommand createReadCommand(SelectionManager aSelectionManager,
			SocketChannel aSocketChannel, Integer amInterestOps);	
}
