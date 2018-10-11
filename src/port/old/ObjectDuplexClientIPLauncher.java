package port.old;

import java.util.Scanner;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import util.trace.Tracer;


public class ObjectDuplexClientIPLauncher {
	public static void launchTypedIPClient(String clientName) {	
		Tracer.showInfo(true);
		DuplexClientInputPort<Object> clientInputPort = DuplexObjectInputPortSelector.createDuplexClientInputPort("localhost", "9090", "test server", clientName);		
		PrintingTypedReceiveListener printingTypedReceiveListener = new PrintingTypedReceiveListener(clientInputPort);
		clientInputPort.addConnectionListener(printingTypedReceiveListener);
//		clientInputPort.addDisconnectListener(printingTypedReceiveListener);
//		clientInputPort.addReceiveListener(printingTypedReceiveListener);
//		clientInputPort.addReceiveListener(printingTypedReceiveListener);
		clientInputPort.addSendListener(printingTypedReceiveListener);
		clientInputPort.addReceiveListener(printingTypedReceiveListener);

		clientInputPort.connect();
//		ByteBuffer message =  ByteBuffer.wrap("hello server".getBytes());
//		System.out.println("String with padding:" + new String(message.array()));		
//		clientInputPort.send(message);
		String stringMessage = "hello server";
//		ByteBuffer message = ByteBuffer.wrap(stringMessage.getBytes());
		Scanner in = new Scanner(System.in);	
		while (true) {
			clientInputPort.send(stringMessage);
		    stringMessage  = in.nextLine();
		}
	}	
	
	
	public static void main (String[] args) {
		launchTypedIPClient("test client");
	}

}
