package port.old;

import java.nio.ByteBuffer;

import extraip.APrintingReceiveAndSendListener;
import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexInputPortFactory;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;
import inputport.datacomm.duplex.buffer.nio.AnNIODuplexBufferInputPortFactory;





public class AClientDuplexLauncher {
	public static void launchDuplexClient(DuplexInputPortFactory factory, String clientName) {
//		Tracer.showInfo(true);
		DuplexBufferInputPortSelector.setDuplexBufferInputPortFactory(factory);
		DuplexClientInputPort clientInputPort = DuplexBufferInputPortSelector.createDuplexClientInputPort("localhost", "9090", "test server", clientName);
		APrintingReceiveAndSendListener echoingConnectionListener = new APrintingReceiveAndSendListener(clientInputPort);
		clientInputPort.addConnectionListener(echoingConnectionListener);
//		clientInputPort.addDisconnectListener(echoingConnectionListener);
		clientInputPort.addReceiveListener(echoingConnectionListener);
		clientInputPort.addSendListener(echoingConnectionListener);
		clientInputPort.connect();
		ByteBuffer message =  ByteBuffer.wrap("hello server".getBytes());
		System.out.println("String with padding:" + new String(message.array()));		
		clientInputPort.send(message);		
	}
	public static void launchDuplexNIOClient(String clientName) {
		DuplexInputPortFactory inputPortFactory = new AnNIODuplexBufferInputPortFactory();
		launchDuplexClient(inputPortFactory, clientName);
	}
	
	
	public static void main (String[] args) {
		launchDuplexNIOClient("test client");
	}

}
