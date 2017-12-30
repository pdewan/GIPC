package inputport.nio.manager;

import java.net.InetAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

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
