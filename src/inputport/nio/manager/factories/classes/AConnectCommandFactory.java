package inputport.nio.manager.factories.classes;

import java.net.InetAddress;
import java.nio.channels.SocketChannel;

import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.ConnectCommand;
import inputport.nio.manager.commands.classes.AConnectCommand;
import inputport.nio.manager.factories.ConnectCommandFactory;

/**
 * A factory for creating connect commands. The main difference between
 * command factories is the interest ops for the selector after the corresponding
 * operation is executed. This factory makes it null, essentially specifying no
 * interestop. An application wanting reading should create a factory that
 * makes it the read interest op. A factory wanting to write need not set the
 * interestop as the write operation will change the interest op.
 */
public class AConnectCommandFactory implements ConnectCommandFactory {
	Integer initialInterestOp;
	public AConnectCommandFactory() {
		
	}
	/**
	 * 
	 * @param anInitialInterestOps
	 * The argument interestops, indicates the  interest ops of the channel after its connect
	 * completes.
	 * In the default implementation of command objects these are the steady state interest ops of 
	 * this channel after the connect.
	 * They are changed only by writes.
	 * The start of a write changes the interestops to OP_WRITE and the end of the 
	 * write changes the interestops before the write initiation. 
	 * So in a non write state, the channel has the initial interest ops.
	 */
	public AConnectCommandFactory(Integer anInitialInterstOp) {
		initialInterestOp = anInitialInterstOp;
	}
	@Override
	public ConnectCommand createConnectCommand(
			SelectionManager aSelectionManager, SocketChannel aSocketChannel,
			InetAddress aServerHost, int aPort) {
		return new AConnectCommand (aSelectionManager, aSocketChannel, aServerHost, aPort, initialInterestOp);
	}

	@Override
	public ConnectCommand createConnectCommand(
			SelectionManager aSelectionManager, SocketChannel aSocketChannel,
			InetAddress aServerHost, int aPort, Integer anInitialInterestOps) {
		return new AConnectCommand(aSelectionManager, aSocketChannel, aServerHost, aPort, anInitialInterestOps);
	}

}
