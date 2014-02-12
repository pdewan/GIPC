package port.old;

import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.object.SimplexObjectInputPortSelector;
import util.trace.Tracer;






public class AnObjectClientIPLauncher {
	public static void launchTypedIPClient(String clientName) {	
		Tracer.showInfo(true);
		SimplexClientInputPort<Object> clientInputPort = SimplexObjectInputPortSelector.createSimplexClientInputPort("localhost", "9090", "test server", clientName);		
		PrintingTypedReceiveListener printingTypedReceiveListener = new PrintingTypedReceiveListener(clientInputPort);
		clientInputPort.addConnectionListener(printingTypedReceiveListener);
//		clientInputPort.addDisconnectListener(printingTypedReceiveListener);
//		clientInputPort.addReceiveListener(printingTypedReceiveListener);
//		clientInputPort.addReceiveListener(printingTypedReceiveListener);
		clientInputPort.addSendListener(printingTypedReceiveListener);

		clientInputPort.connect();
//		ByteBuffer message =  ByteBuffer.wrap("hello server".getBytes());
//		System.out.println("String with padding:" + new String(message.array()));		
//		clientInputPort.send(message);
		clientInputPort.send("hello from" + clientName);
	}	
	
	
	public static void main (String[] args) {
		launchTypedIPClient("test client");
	}

}
