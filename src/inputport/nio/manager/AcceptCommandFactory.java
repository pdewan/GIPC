package inputport.nio.manager;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface AcceptCommandFactory {	
	AcceptCommand createAcceptCommand (
			SelectionManager aSelectingRunnable, ServerSocketChannel aSocketChannel);
	AcceptCommand createAcceptCommand (
			SelectionManager aSelectingRunnable, ServerSocketChannel aSocketChannel, 
			Integer anInterestOps);

}
