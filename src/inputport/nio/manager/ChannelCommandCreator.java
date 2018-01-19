package inputport.nio.manager;

import inputport.nio.manager.commands.AcceptCommand;
import inputport.nio.manager.commands.ConnectCommand;
import inputport.nio.manager.commands.HeaderWriteCommand;
import inputport.nio.manager.commands.ReadCommand;
import inputport.nio.manager.commands.WriteCommand;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface ChannelCommandCreator {
	ConnectCommand createConnectCommand(SelectionManager aSelectionManager, SocketChannel aSocketChannel, InetAddress aServerHost, int aPort) ;
	AcceptCommand createAcceptCommand (SelectionManager aSelectingRunnable, ServerSocketChannel aSocketChannel);
	ReadCommand createReadCommand(SelectionManager aSelectionManager,
			SocketChannel aSocketChannel);
	HeaderWriteCommand createHeaderWriteCommand(MessagingSelectingRunnable aMessageSelectingRunnable,
			SocketChannel aSocketChannel);				
	WriteCommand createWriteCommand (SelectionManager aSelectingRunnable, SocketChannel  aSocketChannel, ByteBuffer aWriteBuffer);

}
