package inputport.nio.manager.commands.classes;

import java.nio.channels.SelectionKey;
import java.nio.channels.spi.AbstractSelectableChannel;

import util.trace.port.nio.SelectorRequestNextInterestOp;
import util.trace.port.nio.SocketChannelInterestOp;

public  class AnAbstractNIOCommand {
	Integer nextInterestOps;
	
	public AnAbstractNIOCommand(AbstractSelectableChannel aChannel, Integer aNextInterestOps) {
		nextInterestOps = aNextInterestOps;
//		if (nextInterestOps != null) {
//			SelectorRequestNextInterestOp.newCase(this, aChannel, aNextInterestOps);
//		}
	}
	protected Integer postCommandInterestOps() {
		return nextInterestOps;
	}
	public void changeInterestOps(SelectionKey aSelectionKey) {
		changeInterestOps(aSelectionKey, nextInterestOps);
	}
	
	public void changeInterestOps(SelectionKey aSelectionKey, Integer aNextOps) {
		if (aNextOps == null) 
			aNextOps = 0;
		aSelectionKey.interestOps(aNextOps);
		SocketChannelInterestOp.newCase(this, aSelectionKey, aNextOps);
//		SocketChannelInterestOp.newCase(this, aSelectionKey, aNextOps);
	}
}
