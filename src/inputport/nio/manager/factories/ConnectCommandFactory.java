package inputport.nio.manager.factories;

import java.net.InetAddress;
import java.nio.channels.SocketChannel;

import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.ConnectCommand;

public interface ConnectCommandFactory {
	ConnectCommand createConnectCommand(SelectionManager aSelectionManager, SocketChannel aSocketChannel, InetAddress aServerHost, int aPort) ;
	ConnectCommand createConnectCommand(SelectionManager aSelectionManager, SocketChannel aSocketChannel, InetAddress aServerHost, int aPort, Integer anInterestOps) ;


}
