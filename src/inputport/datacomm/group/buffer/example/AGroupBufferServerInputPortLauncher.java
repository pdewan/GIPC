package inputport.datacomm.group.buffer.example;

import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.duplex.buffer.echoer.example.ADuplexBufferServerInputPortLauncher;
import inputport.datacomm.group.GroupInputPort;
import inputport.datacomm.group.buffer.AGroupBufferInputPortLauncherSupport;
import inputport.datacomm.group.buffer.BufferGroupInputPortSelector;
import port.PortLauncherSupport;

public class AGroupBufferServerInputPortLauncher extends ADuplexBufferServerInputPortLauncher  {
	protected PortLauncherSupport getPortLauncherSupport() {
		return new AGroupBufferInputPortLauncherSupport();
	}
	@Override
	protected InputPort getPort() {
		return BufferGroupInputPortSelector.createGroupServerInputPort(
				SERVER_ID, 
				UPPECASE_SERVER_NAME);
	}
	@Override
	protected ReceiveListener getReceiveListener (InputPort anInputPort) {
		return new AMulticastingReceiveListener((GroupInputPort) anInputPort );
	}
	
	public static void main (String[] args) {
		(new AGroupBufferServerInputPortLauncher()).launch();
	}
//	public static void main (String[] args) {
//		setTracing();
//		GroupServerInputPort<ByteBuffer> aServerInputPort = 
//			BufferGroupInputPortSelector.createGroupServerInputPort(
//				SERVER_PORT, 
//				SERVER_NAME);
//		addListeners(aServerInputPort);	
//		aServerInputPort.connect();		
//	}	
//	public static void addListeners(GroupServerInputPort<ByteBuffer> aGroupPort) {
//		ADuplexBufferServerInputPortLauncher.addListeners(aGroupPort);
//		aGroupPort.addReceiveListener(new AMulticastingReceiveListener(aGroupPort));
//	}
}
