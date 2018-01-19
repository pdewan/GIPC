package inputport.nio.manager.factories.classes;

import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.AcceptCommand;
import inputport.nio.manager.commands.classes.AnAcceptCommand;

import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
/**
 * A factory that creates an {@link AcceptCommand} that enables reads after
 * the accept operation finishes. Should be used by any application that 
 * allows reads on the socketchannel. The default factory does not enable reads.
 * @author Dewan
 *
 */
public class AReadingAcceptCommandFactory extends AnAcceptCommandFactory{
	public AcceptCommand createAcceptCommand(
			SelectionManager aSelectingRunnable,
			ServerSocketChannel aSocketChannel) {
		return new AnAcceptCommand(aSelectingRunnable, aSocketChannel, SelectionKey.OP_READ);
	}
}
