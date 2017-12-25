package inputport.datacomm.simplex.buffer.nio;

import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

public class AReadingAcceptCommandFactory extends AnAcceptCommandFactory{
	public AcceptCommand createAcceptCommand(
			SelectionManager aSelectingRunnable,
			ServerSocketChannel aSocketChannel) {
		return new AnAcceptCommand(aSelectingRunnable, aSocketChannel, SelectionKey.OP_READ);
	}
}
