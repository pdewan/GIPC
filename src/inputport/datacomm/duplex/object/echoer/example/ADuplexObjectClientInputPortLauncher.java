package inputport.datacomm.duplex.object.echoer.example;

import java.nio.ByteBuffer;

import port.PortLauncherSupport;

import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.Sender;
import inputport.datacomm.duplex.buffer.echoer.example.ADuplexBufferClientInputPortLauncher;
import inputport.datacomm.duplex.object.ADuplexObjectInputPortLauncherSupport;
import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.buffer.example.ASimplexBufferFrostyConsoleUI;
import inputport.datacomm.simplex.buffer.example.UserInterfaceManager;
import inputport.datacomm.simplex.object.example.ASimplexObjectFrostyConsoleUI;
import inputport.datacomm.simplex.object.example.AnEchoingObjectReceiveListener;




public class ADuplexObjectClientInputPortLauncher extends ADuplexBufferClientInputPortLauncher {
	public ADuplexObjectClientInputPortLauncher(String aClientName) {
		super(aClientName);
	}
	@Override
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ADuplexObjectInputPortLauncherSupport();
	}
	@Override
	protected ReceiveListener getReceiveListener (InputPort anInputPort) {
		return new AnEchoingObjectReceiveListener(anInputPort);
	}		
	@Override
	protected InputPort getPort() {
		return DuplexObjectInputPortSelector.createDuplexClientInputPort(
				SERVER_HOST, SERVER_ID, SERVER_NAME, 	clientName);
	}
	protected UserInterfaceManager createUserInterfaceManager(InputPort anInputPort) {
		return new ASimplexObjectFrostyConsoleUI((SimplexClientInputPort<Object>) anInputPort);
	}


//	protected void send(InputPort anInputPort, String aMessage) {
//		Sender<Object> aSender = (Sender<Object>) anInputPort;
//		aSender.send(aMessage);
//	}

	
}
