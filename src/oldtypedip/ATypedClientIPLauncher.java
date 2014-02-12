package oldtypedip;





public class ATypedClientIPLauncher {
	public static void launchTypedIPClient(String clientName) {	
//		Message.showInfo(true);
		TypedClientInputPort clientInputPort = new AnInheritingTypedClientInputPort("localhost", "9090", clientName);
		PrintingTypedReceiveListener echoingConnectionListener = new PrintingTypedReceiveListener(null);
		clientInputPort.addConnectionListener(echoingConnectionListener);
		clientInputPort.addDisconnectListener(echoingConnectionListener);

		clientInputPort.addTypedReceiveListener(echoingConnectionListener);
		clientInputPort.addSendListener(echoingConnectionListener);

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
