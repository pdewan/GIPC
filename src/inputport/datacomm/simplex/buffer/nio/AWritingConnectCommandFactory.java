package inputport.datacomm.simplex.buffer.nio;

import java.net.InetAddress;
import java.nio.channels.SocketChannel;

import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.ConnectCommand;
import inputport.nio.manager.commands.classes.AConnectCommand;
import inputport.nio.manager.factories.ConnectCommandFactory;
import inputport.nio.manager.factories.classes.AConnectCommandFactory;

public class AWritingConnectCommandFactory extends AConnectCommandFactory implements ConnectCommandFactory {

	@Override
	public ConnectCommand createConnectCommand(
			SelectionManager aSelectionManager, SocketChannel aSocketChannel,
			InetAddress aServerHost, int aPort) {
		// write will set the interestops
		return new AConnectCommand (aSelectionManager, aSocketChannel, aServerHost, aPort, null);
	}

	

}
