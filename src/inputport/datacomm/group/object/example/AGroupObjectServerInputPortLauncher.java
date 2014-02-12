package inputport.datacomm.group.object.example;

import port.PortLauncherSupport;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.duplex.object.echoer.example.ADuplexObjectServerInputPortLauncher;
import inputport.datacomm.group.GroupInputPort;
import inputport.datacomm.group.object.AGroupObjectInputPortLauncherSupport;
import inputport.datacomm.group.object.GroupObjectInputPortSelector;

public class AGroupObjectServerInputPortLauncher extends ADuplexObjectServerInputPortLauncher  {
	@Override
	protected PortLauncherSupport getPortLauncherSupport() {
		return new AGroupObjectInputPortLauncherSupport();
	}
	@Override
	protected InputPort getPort() {
		return GroupObjectInputPortSelector.createGroupServerInputPort(
				SERVER_ID, 
				UPPECASE_SERVER_NAME);
	}
	@Override
	protected ReceiveListener getReceiveListener(InputPort anInputPort) {
		return new  AMulticastingObjectReceiveListener((GroupInputPort)anInputPort);
	}
//	public static void main (String[] args) {
//		(new AGroupObjectServerInputPortLauncher()).launch();
//	}
//	public static void main (String[] args) {
//		setTracing();
//		GroupServerInputPort<Object> aServerInputPort = 
//			ObjectGroupInputPortSelector.createGroupServerInputPort(
//				SERVER_PORT, 
//				SERVER_NAME);
//		addListeners(aServerInputPort);	
//		aServerInputPort.connect();		
//	}	
//	public static void addListeners(GroupServerInputPort<Object> aServerInputPort) {
//		ADuplexObjectServerInputPortLauncher.addListeners(aServerInputPort);
//		aServerInputPort.addReceiveListener(new AMulticastingObjectReceiveListener(aServerInputPort));
//
//	}
}
