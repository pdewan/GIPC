package port.old;

import inputport.datacomm.simplex.buffer.nio.SelectionManager;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import extraip.MonolithicNIODuplexClientInputPort;

public class AMonolithicNIODuplexClientInputPort extends AMonolithicNIOClientInputPort implements MonolithicNIODuplexClientInputPort{
	public AMonolithicNIODuplexClientInputPort(SelectionManager theSelectingRunnable,
			String theRemoteHostName, String theRemotePort, String theMyName) {
		super(theSelectingRunnable, theRemoteHostName, theRemotePort, theMyName);		
	}
	@Override
	public void connected(SocketChannel theSocketChannel) {
		super.connected(theSocketChannel);
		selectingRunnable.registerReadListener(theSocketChannel, this);
		
	}
	@Override
	public void socketChannelRead(SocketChannel theSocketChannel,
			ByteBuffer theMessage) {
		notifyPortReceive(serverName, theMessage, theMessage.limit() - theMessage.position());		
	}
	
	public static void main (String[] args) {
		MonolithicDuplexInputPortFactory inputPortFactory = new AMonolithicNIODuplexInputPortFactory();
//		DuplexInputPortSelector.setInputPortFactory(inputPortFactory);
		MonolithicDuplexClientInputPort clientInputPort = inputPortFactory.createDuplexClientInputPort("localhost", "9090", "test client");
		MonolithicPrintingReceiveListener echoingConnectionListener = new MonolithicPrintingReceiveListener(null);
		clientInputPort.addConnectionListener(echoingConnectionListener);
		clientInputPort.addDisconnectListener(echoingConnectionListener);
		clientInputPort.addReceiveListener(echoingConnectionListener);
		clientInputPort.connect();
		ByteBuffer message =  ByteBuffer.wrap("hello server".getBytes());
//		byte[] padding = new byte[10];
//		message.position(message.limit());
//		message.put(padding);
//		message.flip();
		System.out.println("String with padding:" + new String(message.array()));		
		clientInputPort.send(message);
	}
	@Override
	public void reply(ByteBuffer message) {
//		return send(message);
		send(message);
	}
//	@Override
//	public void send(String remoteName, ByteBuffer message) {
//		send(message);
//		
//	}

}
