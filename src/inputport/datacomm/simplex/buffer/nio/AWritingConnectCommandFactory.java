package inputport.datacomm.simplex.buffer.nio;

import inputport.nio.manager.AConnectCommand;
import inputport.nio.manager.AConnectCommandFactory;
import inputport.nio.manager.ConnectCommand;
import inputport.nio.manager.ConnectCommandFactory;
import inputport.nio.manager.SelectionManager;

import java.net.InetAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class AWritingConnectCommandFactory extends AConnectCommandFactory implements ConnectCommandFactory {

	@Override
	public ConnectCommand createConnectCommand(
			SelectionManager aSelectionManager, SocketChannel aSocketChannel,
			InetAddress aServerHost, int aPort) {
		// write will set the interestops
		return new AConnectCommand (aSelectionManager, aSocketChannel, aServerHost, aPort, null);
	}

	

}
