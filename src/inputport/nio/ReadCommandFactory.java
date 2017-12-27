package inputport.nio;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface ReadCommandFactory {	
	ReadCommand createReadCommand(SelectionManager aSelectionManager,
			SocketChannel aSocketChannel);	
	ReadCommand createReadCommand(SelectionManager aSelectionManager,
			SocketChannel aSocketChannel, Integer amInterestOps);	
}
