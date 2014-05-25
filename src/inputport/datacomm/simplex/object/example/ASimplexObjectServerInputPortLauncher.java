package inputport.datacomm.simplex.object.example;

import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.simplex.buffer.example.ASimplexBufferServerInputPortLauncher;
import inputport.datacomm.simplex.object.ASimplexObjectInputPortLauncherSupport;
import inputport.datacomm.simplex.object.SimplexObjectInputPortSelector;
import port.PortLauncherSupport;

public class ASimplexObjectServerInputPortLauncher extends ASimplexBufferServerInputPortLauncher {
public ASimplexObjectServerInputPortLauncher(String aServerName,
			String aServerPort) {
	super(aServerName, aServerPort);
	}
public ASimplexObjectServerInputPortLauncher() {
	// TODO Auto-generated constructor stub
}
	
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ASimplexObjectInputPortLauncherSupport();
	}
	@Override
	protected InputPort getPort() {
		return SimplexObjectInputPortSelector.createSimplexServerInputPort(
				serverId, 
				serverName);
	}
	
	protected ReceiveListener getReceiveListener (InputPort aServerInputPort) {
		return new AnEchoingObjectReceiveListener(aServerInputPort);
	}
	public static void main (String[] args) {
		(new ASimplexObjectServerInputPortLauncher()).launch();
	}
	
}
