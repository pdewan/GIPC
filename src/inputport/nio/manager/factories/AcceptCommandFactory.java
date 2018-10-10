package inputport.nio.manager.factories;

import java.nio.channels.ServerSocketChannel;

import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.AcceptCommand;

public interface AcceptCommandFactory {	
	AcceptCommand createAcceptCommand (
			SelectionManager aSelectingRunnable, ServerSocketChannel aSocketChannel);
	AcceptCommand createAcceptCommand (
			SelectionManager aSelectingRunnable, ServerSocketChannel aSocketChannel, 
			Integer anInterestOps);

}
