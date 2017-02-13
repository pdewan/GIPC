package inputport.datacomm.simplex.buffer.example;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.buffer.ASimplexBufferInputPortLauncherSupport;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;
import inputport.datacomm.simplex.buffer.SimplexBufferInputPortSelector;

import java.nio.ByteBuffer;

import port.ATracingConnectionListener;
import port.AnAbstractPortLauncher;
import port.PortAccessKind;
import port.PortKind;
import port.PortLauncherSupport;
import port.PortMessageKind;




//public class AClientSimplexBufferInputPortLauncher extends ASimplexBufferInputPortLauncher{
public class ASimplexBufferClientInputPortLauncher extends AnAbstractPortLauncher {

//	public  static final String SERVER_HOST = "localhost";
//	protected String clientName;
	public static final String SERVER_NAME = ASimplexBufferServerInputPortLauncher.UPPECASE_SERVER_NAME;
	public ASimplexBufferClientInputPortLauncher(String aClientName, String aServerHost, String aServerId, String aServerName) {
		super(aClientName, aServerHost, aServerId, aServerName);		
	}
	public ASimplexBufferClientInputPortLauncher(String aClientName) {
		super ( aClientName);
	}
//	@Override
//	public  void launch () {
//		super.launch();
////		createUI(mainPort);
//
//	}
	public PortAccessKind getPortAccessKind() {
		return PortAccessKind.SIMPLEX;
	}
	public PortKind getPortKind() {
		return PortKind.CLIENT_INPUT_PORT;
	}
	public PortMessageKind getPortMessageKind() {
		return PortMessageKind.BUFFER;
	}

	protected PortLauncherSupport getPortLauncherSupport() {
		return new ASimplexBufferInputPortLauncherSupport();
	}
	@Override
	protected InputPort getPort() {
		return SimplexBufferInputPortSelector.createSimplexClientInputPort(
				SERVER_HOST, SERVER_ID, SERVER_NAME, 	clientName);
	}

	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
		return new ATracingConnectionListener(anInputPort);
	}
	
	protected ByteBufferSendListener getSendListener (InputPort anInputPort) {
		return new ATracingSendListener(anInputPort);
	}
	
	protected UserInterfaceManager createUserInterfaceManager(InputPort anInputPort) {
		return new ASimplexBufferFrostyConsoleUI((SimplexClientInputPort<ByteBuffer>) anInputPort);
	}

	@Override
	protected  void createUI (InputPort anInputPort) {
		UserInterfaceManager userInterfaceManager = createUserInterfaceManager(anInputPort);
		userInterfaceManager.manageUserInterface();

	}

	
}
