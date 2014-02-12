package inputport.datacomm.duplex.buffer.echoer.example;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.buffer.ADuplexBufferInputPortLauncherSupport;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;
import inputport.datacomm.simplex.buffer.example.ASimplexBufferServerInputPortLauncher;
import inputport.datacomm.simplex.buffer.example.ATracingSendListener;

import java.nio.ByteBuffer;

import port.PortLauncherSupport;

public class ADuplexBufferServerInputPortLauncher extends ASimplexBufferServerInputPortLauncher  {
	
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ADuplexBufferInputPortLauncherSupport();
	}
	@Override
	protected InputPort getPort() {
		return DuplexBufferInputPortSelector.createDuplexServerInputPort(
				SERVER_ID, 
				UPPECASE_SERVER_NAME);
	}

	
	@Override
	protected ConnectionListener getConnectionListener (InputPort aServerInputPort) {
		return new AFrostyBufferConnectionListener((DuplexServerInputPort<ByteBuffer>) aServerInputPort);
	}
	@Override
	protected ReceiveListener getReceiveListener (InputPort aServerInputPort) {
		return new AReplyingUpperCaseBufferReceiveListener(aServerInputPort);
	}	
	
	protected ByteBufferSendListener getSendListener (InputPort aServerInputPort) {
		return new ATracingSendListener(aServerInputPort);
	}
	public static void main (String[] args) {		
		(new ADuplexBufferServerInputPortLauncher()).launch();
		
	}
	
	
}
