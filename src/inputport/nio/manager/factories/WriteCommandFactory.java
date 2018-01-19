package inputport.nio.manager.factories;

import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.WriteCommand;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface WriteCommandFactory {			
	WriteCommand createWriteCommand (
			SelectionManager aSelectingRunnable, SocketChannel  aSocketChannel, ByteBuffer aWriteBuffer);
	WriteCommand createWriteCommand (
			SelectionManager aSelectingRunnable, SocketChannel  aSocketChannel, ByteBuffer aWriteBuffer, Integer anInterestOps);

}
