package inputport.nio.manager.factories.classes;

import java.net.InetAddress;
import java.nio.channels.SocketChannel;

import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.ConnectCommand;
import inputport.nio.manager.commands.classes.AConnectCommand;
import inputport.nio.manager.factories.ConnectCommandFactory;

/*
 * A factory for creating connect commands. The main difference between
 * command factories is the interest ops for the selector after the corresponding
 * operation is executed. This factory makes it null, essentially specifying no
 * interestop. An application wanting reading should create a factory that
 * makes it the read interest op. A factory wanting to write need not set the
 * interestop as the write operation will change the interest op.
 */
public class AConnectCommandFactory implements ConnectCommandFactory {

	@Override
	public ConnectCommand createConnectCommand(
			SelectionManager aSelectionManager, SocketChannel aSocketChannel,
			InetAddress aServerHost, int aPort) {
		return new AConnectCommand (aSelectionManager, aSocketChannel, aServerHost, aPort, null);
	}

	@Override
	public ConnectCommand createConnectCommand(
			SelectionManager aSelectionManager, SocketChannel aSocketChannel,
			InetAddress aServerHost, int aPort, Integer aNextInterestOps) {
		return new AConnectCommand(aSelectionManager, aSocketChannel, aServerHost, aPort, aNextInterestOps);
	}

}
