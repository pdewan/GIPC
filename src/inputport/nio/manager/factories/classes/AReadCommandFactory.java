package inputport.nio.manager.factories.classes;

import java.nio.channels.SocketChannel;

import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.ReadCommand;
import inputport.nio.manager.commands.classes.AScatterGatherReadCommand;
import inputport.nio.manager.factories.ReadCommandFactory;

/*
 * A factory for creating read commands. The main difference between
 * command factories is the interest ops for the selector after the corresponding
 * operation is executed. This factory makes it null, essentially specifying no
 * interestop. 
 */
public class AReadCommandFactory implements ReadCommandFactory {

	@Override
	public ReadCommand createReadCommand(SelectionManager aSelectionManager,
			SocketChannel aSocketChannel) {
		return createReadCommand(aSelectionManager, aSocketChannel, null);
	}

	@Override
	public ReadCommand createReadCommand(SelectionManager aSelectionManager,
			SocketChannel aSocketChannel, Integer anInterestOps) {
//		return new AReadCommand(aSelectionManager, aSocketChannel, anInterestOps);
		return new AScatterGatherReadCommand(aSelectionManager, aSocketChannel, anInterestOps);
	}

	
	
}
