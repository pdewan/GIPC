package inputport.nio.manager.factories.classes;

import java.net.InetAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import inputport.datacomm.simplex.buffer.nio.AWritingConnectCommandFactory;
import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.AcceptCommand;
import inputport.nio.manager.commands.ConnectCommand;
import inputport.nio.manager.commands.classes.AConnectCommand;
/**
 * A factory that creates a {@link ConnectCommand} that enables reads after
 * the connect operation finishes. Should be used by any application that 
 * allows reads on the socketchannel. The default factory does not enable reads.
 * @author Dewan
 *
 */
public class AReadingWritingConnectCommandFactory extends AConnectCommandFactory {
	@Override
	public ConnectCommand createConnectCommand(
			SelectionManager aSelectionManager, SocketChannel aSocketChannel,
			InetAddress aServerHost, int aPort) {
		return new AConnectCommand (aSelectionManager, aSocketChannel, aServerHost, aPort, SelectionKey.OP_READ);
	}
}
