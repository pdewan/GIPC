package inputport.rpc.duplex;

import inputport.datacomm.ReceiveListener;
import sessionport.datacomm.duplex.object.relayed.AMessageWithSource;

public class AMessageWithSourceAndReceiver 
			extends AMessageWithSource
			implements MessageWithSourceAndReceiver {
	ReceiveListener receiver;
	public AMessageWithSourceAndReceiver(String aSource, Object aMessage, ReceiveListener aReceiver) {
		super(aSource, aMessage);
		receiver = aReceiver;
	}
	@Override
	public ReceiveListener getReceiveListener() {
		return receiver;
	}
	@Override
	public void setReceiveListener(ReceiveListener newVal) {
		receiver = newVal;
	}

}
