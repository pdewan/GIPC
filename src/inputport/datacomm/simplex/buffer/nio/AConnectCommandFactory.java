package inputport.datacomm.simplex.buffer.nio;

import java.net.InetAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

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
			InetAddress aServerHost, int aPort, Integer anInterestOps) {
		return new AConnectCommand(aSelectionManager, aSocketChannel, aServerHost, aPort, anInterestOps);
	}

}
