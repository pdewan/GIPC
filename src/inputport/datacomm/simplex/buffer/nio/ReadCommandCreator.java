package inputport.datacomm.simplex.buffer.nio;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface ReadCommandCreator {	
	ReadCommand createReadCommand(SelectionManager aSelectionManager,
			SocketChannel aSocketChannel);	
}
