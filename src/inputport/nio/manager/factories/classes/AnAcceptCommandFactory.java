package inputport.nio.manager.factories.classes;

import java.nio.channels.ServerSocketChannel;

import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.AcceptCommand;
import inputport.nio.manager.commands.classes.AnAcceptCommand;
import inputport.nio.manager.factories.AcceptCommandFactory;

public class AnAcceptCommandFactory implements AcceptCommandFactory {
	Integer initialInterestOps = null;
	/**
	 * 
	 * @param anInitialInterestOps
	 * The argument interestops, indicates the initial interest ops of the channel returned
	 * in the accept callback. 
	 * In the default implementation of command objects these are the steady state interest ops of 
	 * this channel.
	 * They are changed only by writes.
	 * The start of a write changes the interestops to OP_WRITE and the end of the 
	 * write changes the interestops before the write initiation. 
	 * So in a non write state, the channel has the initial interest ops.
	 */
	public AnAcceptCommandFactory(Integer anInitialInterestOps) {
		initialInterestOps = anInitialInterestOps;
	}
	public AnAcceptCommandFactory() {
	}
	
	@Override
	public AcceptCommand createAcceptCommand(
			SelectionManager aSelectingRunnable,
			ServerSocketChannel aSocketChannel) {
		return new AnAcceptCommand(aSelectingRunnable, aSocketChannel, initialInterestOps);
	}

	@Override
	public AcceptCommand createAcceptCommand(
			SelectionManager aSelectingRunnable,
			ServerSocketChannel aSocketChannel, Integer aNextInterestOps) {
		// TODO Auto-generated method stub
		return new AnAcceptCommand(aSelectingRunnable, aSocketChannel, aNextInterestOps);
	}

}
