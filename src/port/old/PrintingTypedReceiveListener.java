package port.old;

import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import extraip.ASendConnectionListener;

public class PrintingTypedReceiveListener extends ASendConnectionListener implements ReceiveListener<Object> {
	public PrintingTypedReceiveListener(
			InputPort clientInputPort) {
		super(clientInputPort);
	}

	public void messageReceived(String senderName, Object message) {
		System.out.println("received message:" + message + " from " + senderName);
		//return null;
	}

}
