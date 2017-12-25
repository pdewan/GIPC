package inputport.datacomm.simplex.buffer.nio;

import java.nio.channels.SelectionKey;

import port.trace.nio.SocketChannelInterestOp;

public  class AnAbstractNIOCommand {
	Integer nextInterestOps;
	public AnAbstractNIOCommand(Integer aNextInterestOps) {
		nextInterestOps = aNextInterestOps;
	}
	protected Integer postCommandInterestOps() {
		return nextInterestOps;
	}
	
	public void changeInterestOps(SelectionKey aSelectionKey, Integer aNextOps) {
		if (aNextOps == null) return;
		aSelectionKey.interestOps(aNextOps);
		SocketChannelInterestOp.newCase(this, aSelectionKey, aNextOps);
		SocketChannelInterestOp.newCase(this, aSelectionKey, aNextOps);
	}
}
