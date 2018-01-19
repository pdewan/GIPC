package inputport.nio.manager.factories.classes;

import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.ConnectCommand;
import inputport.nio.manager.commands.WriteCommand;
import inputport.nio.manager.commands.classes.AConnectCommand;
import inputport.nio.manager.commands.classes.AWriteCommand;
import inputport.nio.manager.factories.ConnectCommandFactory;
import inputport.nio.manager.factories.WriteCommandFactory;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/*
 * A factory for creating write commands. The main difference between
 * command factories is the interest ops for the selector after the corresponding
 * operation is executed. This factory makes it null, essentially specifying no
 * interestop. An application wanting reading should create a factory that
 * makes it the read interest op. A factory wanting to write need not set the
 * interestop as the write operation will change the interest op.
 */
public class AWriteCommandFactory implements WriteCommandFactory {

	@Override
	public WriteCommand createWriteCommand(SelectionManager aSelectingRunnable,
			SocketChannel aSocketChannel, ByteBuffer aWriteBuffer) {
		return createWriteCommand(aSelectingRunnable, aSocketChannel, aWriteBuffer, null);
	}

	@Override
	public WriteCommand createWriteCommand(SelectionManager aSelectingRunnable,
			SocketChannel aSocketChannel, ByteBuffer aWriteBuffer,
			Integer anInterestOps) {
		return new AWriteCommand(aSelectingRunnable, aSocketChannel, aWriteBuffer, anInterestOps);
	}

	
}
