package inputport.datacomm.simplex.buffer.nio;

import inputport.nio.AcceptCommand;
import inputport.nio.AnAcceptCommand;
import inputport.nio.AnAcceptCommandFactory;
import inputport.nio.SelectionManager;

import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

public class AReadingAcceptCommandFactory extends AnAcceptCommandFactory{
	public AcceptCommand createAcceptCommand(
			SelectionManager aSelectingRunnable,
			ServerSocketChannel aSocketChannel) {
		return new AnAcceptCommand(aSelectingRunnable, aSocketChannel, SelectionKey.OP_READ);
	}
}
