package inputport.nio.manager.factories.classes;

import java.nio.channels.ServerSocketChannel;

import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.AcceptCommand;
import inputport.nio.manager.commands.classes.AnAcceptCommand;
import inputport.nio.manager.factories.AcceptCommandFactory;

public class AnAcceptCommandFactory implements AcceptCommandFactory {

	
	@Override
	public AcceptCommand createAcceptCommand(
			SelectionManager aSelectingRunnable,
			ServerSocketChannel aSocketChannel) {
		return new AnAcceptCommand(aSelectingRunnable, aSocketChannel);
	}

	@Override
	public AcceptCommand createAcceptCommand(
			SelectionManager aSelectingRunnable,
			ServerSocketChannel aSocketChannel, Integer aNextInterestOps) {
		// TODO Auto-generated method stub
		return new AnAcceptCommand(aSelectingRunnable, aSocketChannel, aNextInterestOps);
	}

}
