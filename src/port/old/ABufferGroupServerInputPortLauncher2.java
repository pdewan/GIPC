package port.old;

import inputport.datacomm.group.GroupServerInputPort;
import inputport.datacomm.group.buffer.BufferGroupInputPortSelector;



public class ABufferGroupServerInputPortLauncher2 {
	public static void main (String[] args) {
//		Tracer.showInfo(true);
		GroupServerInputPort serverInputPort = BufferGroupInputPortSelector.createGroupServerInputPort("9090", "test server");
		serverInputPort.connect();
		PrintingBroadcastingReceiverListener echoingReceiveListener = new PrintingBroadcastingReceiverListener(serverInputPort);
		serverInputPort.addConnectionListener(echoingReceiveListener);
//		serverInputPort.addDisconnectListener(echoingReceiveListener);
		serverInputPort.addReceiveListener(echoingReceiveListener);	
		serverInputPort.addSendListener(echoingReceiveListener);
	}

}
