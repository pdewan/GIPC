package oldtypedip;

import inputport.InputPort;

import java.io.Serializable;

import extraip.ASendConnectionListener;

public class PrintingTypedReceiveListener extends ASendConnectionListener implements TypedReceiveListener {
	public PrintingTypedReceiveListener(InputPort anInputPort) {
		super(anInputPort);
		// TODO Auto-generated constructor stub
	}

	public void messageReceived(String senderName, Serializable message) {
		System.out.println("received message:" + message + " from " + senderName);
		//return null;
	}

}
