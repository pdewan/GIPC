package inputport.datacomm.duplex.object.echoer.example;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.datacomm.duplex.buffer.echoer.example.ADuplexBufferServerInputPortLauncher;
import inputport.datacomm.duplex.object.ADuplexObjectInputPortLauncherSupport;
import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import port.PortLauncherSupport;

public class ADuplexObjectServerInputPortLauncher extends ADuplexBufferServerInputPortLauncher  {
	@Override
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ADuplexObjectInputPortLauncherSupport();
	}
	@Override
	protected InputPort getPort() {
		return DuplexObjectInputPortSelector.createDuplexServerInputPort(
				SERVER_ID, 
				UPPECASE_SERVER_NAME);
	}
	
	@Override
	protected ConnectionListener getConnectionListener(InputPort anInputPort) {
		return new AFrostyObjectConnectionListener((DuplexInputPort<Object>) anInputPort);
	}
	
	@Override
	protected ReceiveListener getReceiveListener(InputPort anInputPort) {
		return new AReplyingEchoingObjectReceiveListener(anInputPort);
	}
	
	public static void main (String[] args) {
	
		(new ADuplexObjectServerInputPortLauncher()).launch();
	}
	
//	
//	
//	public static void addListeners(DuplexServerInputPort<Object> aServerInputPort) {
//		aServerInputPort.addConnectionListener(
//			new AnObjectSendingConnectionListener(aServerInputPort));
//		aServerInputPort.addReceiveListener(
//			new ALocalAndRemoteEchoingObjectReceiveListener(aServerInputPort));
//		aServerInputPort.addSendListener(new ATracingSendListener(aServerInputPort));
//	}
	
//	public static void main (String[] args) {
//		setTracing();
//		DuplexServerInputPort<Object> aServerInputPort = 
//			DuplexObjectInputPortSelector.createDuplexServerInputPort(
//				SERVER_PORT, 
//				SERVER_NAME);
//		addListeners(aServerInputPort);	
//		aServerInputPort.connect();		
//	}	
//	public static void addListeners(DuplexServerInputPort<Object> aServerInputPort) {
//		aServerInputPort.addConnectionListener(
//			new AnObjectSendingConnectionListener(aServerInputPort));
//		aServerInputPort.addReceiveListener(
//			new ALocalAndRemoteEchoingObjectReceiveListener(aServerInputPort));
//		aServerInputPort.addSendListener(new ATracingSendListener(aServerInputPort));
//	}
}
