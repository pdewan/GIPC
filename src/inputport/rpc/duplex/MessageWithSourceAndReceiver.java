package inputport.rpc.duplex;

import inputport.datacomm.ReceiveListener;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public interface MessageWithSourceAndReceiver extends MessageWithSource {
	ReceiveListener getReceiveListener();
	void setReceiveListener(ReceiveListener newVal);
	

}
