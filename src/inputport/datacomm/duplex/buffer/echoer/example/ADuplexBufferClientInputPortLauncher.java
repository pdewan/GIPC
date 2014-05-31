package inputport.datacomm.duplex.buffer.echoer.example;

import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.duplex.buffer.ADuplexBufferInputPortLauncherSupport;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;
import inputport.datacomm.simplex.buffer.AnEchoingBufferReceiveListener;
import inputport.datacomm.simplex.buffer.example.ASimplexBufferClientInputPortLauncher;
import port.PortLauncherSupport;


public class ADuplexBufferClientInputPortLauncher extends ASimplexBufferClientInputPortLauncher{
public ADuplexBufferClientInputPortLauncher(String aClientName) {
		super(aClientName);
	}

	protected PortLauncherSupport getPortLauncherSupport() {
		return new ADuplexBufferInputPortLauncherSupport();
	}
	
	@Override
	protected InputPort getPort() {
		return 
				DuplexBufferInputPortSelector.createDuplexClientInputPort(
						SERVER_HOST, SERVER_ID, SERVER_NAME, 	clientName);
	}
	protected ReceiveListener getReceiveListener (InputPort anInputPort) {
		return new AnEchoingBufferReceiveListener(anInputPort);
	}	
	
	
}
