package inputport.nio.manager;

import java.nio.channels.SelectionKey;

import trace.port.nio.SocketChannelInterestOp;

public  class AnAbstractNIOCommand {
	Integer nextInterestOps;
	public AnAbstractNIOCommand(Integer aNextInterestOps) {
		nextInterestOps = aNextInterestOps;
	}
	protected Integer postCommandInterestOps() {
		return nextInterestOps;
	}
	public void changeInterestOps(SelectionKey aSelectionKey) {
		changeInterestOps(aSelectionKey, nextInterestOps);
	}
	
	public void changeInterestOps(SelectionKey aSelectionKey, Integer aNextOps) {
		if (aNextOps == null) return;
		aSelectionKey.interestOps(aNextOps);
		SocketChannelInterestOp.newCase(this, aSelectionKey, aNextOps);
		SocketChannelInterestOp.newCase(this, aSelectionKey, aNextOps);
	}
}
