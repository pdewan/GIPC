package inputport.nio;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface ConnectCommandFactory {
	ConnectCommand createConnectCommand(SelectionManager aSelectionManager, SocketChannel aSocketChannel, InetAddress aServerHost, int aPort) ;
	ConnectCommand createConnectCommand(SelectionManager aSelectionManager, SocketChannel aSocketChannel, InetAddress aServerHost, int aPort, Integer anInterestOps) ;


}
