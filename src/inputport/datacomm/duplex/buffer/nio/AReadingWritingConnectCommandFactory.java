package inputport.datacomm.duplex.buffer.nio;

import java.net.InetAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import inputport.datacomm.simplex.buffer.nio.AConnectCommand;
import inputport.datacomm.simplex.buffer.nio.AWritingConnectCommandFactory;
import inputport.datacomm.simplex.buffer.nio.ConnectCommand;
import inputport.datacomm.simplex.buffer.nio.SelectionManager;

public class AReadingWritingConnectCommandFactory extends AWritingConnectCommandFactory {
	@Override
	public ConnectCommand createConnectCommand(
			SelectionManager aSelectionManager, SocketChannel aSocketChannel,
			InetAddress aServerHost, int aPort) {
		return new AConnectCommand (aSelectionManager, aSocketChannel, aServerHost, aPort, SelectionKey.OP_READ);
	}
}
