package inputport.datacomm.simplex.buffer.example;

import java.nio.ByteBuffer;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.simplex.SimplexServerInputPort;
import inputport.datacomm.simplex.buffer.SimplexBufferInputPortSelector;
import port.ATracingConnectionListener;
import port.AnAbstractPortLauncher;
import util.trace.port.nio.NIOTraceUtility;

public class ASimplexBufferServerInputPortLauncher extends AnAbstractPortLauncher {
	public static final String UPPECASE_SERVER_NAME = "Upper Case Server";
	public ASimplexBufferServerInputPortLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}
	public ASimplexBufferServerInputPortLauncher() {
		serverName = UPPECASE_SERVER_NAME;
	}
	@Override
	protected ConnectionListener getConnectionListener (InputPort aServerInputPort) {
		return new ATracingConnectionListener((SimplexServerInputPort<ByteBuffer>)aServerInputPort);
	}
	@Override
	protected ReceiveListener getReceiveListener (InputPort aServerInputPort) {
		return new AnUpperCaseBufferReceiveListener(aServerInputPort);
	}	
	@Override
	protected InputPort getPort() {
		return SimplexBufferInputPortSelector.createServerSimplexInputPort(
				serverId, 
				serverName);
	}
	
	public static void main (String[] args) {
		NIOTraceUtility.setTracing();
		(new ASimplexBufferServerInputPortLauncher()).launch();
	}
}
