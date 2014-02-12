package port.old;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;

import java.nio.ByteBuffer;
import java.util.Scanner;

import extraip.APrintingReceiveAndSendListener;



public class BufferDuplexClientInputPortLauncher {
	public static void launch (String[] args) {	
		DuplexClientInputPort clientInputPort = DuplexBufferInputPortSelector.createDuplexClientInputPort("localhost", "9090", "test server", "test client");
//		PrintingReceiveListener echoingConnectionListener = new PrintingReceiveListener();
//		clientInputPort.addConnectListener(echoingConnectionListener);
//		clientInputPort.addReceiveListener(echoingConnectionListener);
//		clientInputPort.connect();
//		ByteBuffer message =  ByteBuffer.wrap("hello server".getBytes());
//		System.out.println("String with padding:" + new String(message.array()));		
//		clientInputPort.send(message);
		APrintingReceiveAndSendListener echoingConnectionListener = new APrintingReceiveAndSendListener(clientInputPort);
		clientInputPort.addConnectionListener(echoingConnectionListener);
		clientInputPort.addReceiveListener(echoingConnectionListener);
		clientInputPort.connect();
		String stringMessage = "hello server";
		ByteBuffer message = ByteBuffer.wrap(stringMessage.getBytes());
		Scanner in = new Scanner(System.in);	
		while (true) {
			clientInputPort.send(message);
		    stringMessage  = in.nextLine();
		    message =  ByteBuffer.wrap(stringMessage.getBytes());		
		}
	}

}
