package port.old;

import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;

public class BufferDuplexServerInputPortLauncher {
	public static void launch (String[] args) {
		DuplexServerInputPort serverInputPort = DuplexBufferInputPortSelector.createDuplexServerInputPort("9090", "test server");
		PrintingReplyingReceiveListener echoingReceiveListener = new PrintingReplyingReceiveListener(serverInputPort);
		serverInputPort.addConnectionListener(echoingReceiveListener);
		serverInputPort.addReceiveListener(echoingReceiveListener);	
		serverInputPort.connect();
	}

}
