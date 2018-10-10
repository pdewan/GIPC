package replicatedserverport.rpc.group.relayed.latecomer.example;

import java.nio.ByteBuffer;

import bus.uigen.ObjectEditor;
import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.simplex.SimplexServerInputPort;
import inputport.datacomm.simplex.buffer.AnEchoingBufferReceiveListener;
import port.ATracingConnectionListener;
import port.AnAbstractPortLauncher;
import port.PortLauncherSupport;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.relay.late.ALatecomerRelayerAndSessionServerLauncherSupport;
import port.sessionserver.relay.late.LatecomerRelayerAndSessionServerSelector;
import util.trace.port.ConnectionEventManagerFactory;

public  class ALatecomerSessionServerLauncher extends AnAbstractPortLauncher{
	public static String SESSION_SERVER_NAME = "Sessions Server";
//	public static String RELAYER_NAME = "Relayer";
	ServerPortDescription serverPortDescription;

	public  ALatecomerSessionServerLauncher (ServerPortDescription aServerPortDescription) {
		serverPortDescription = aServerPortDescription;
		ObjectEditor.edit(ConnectionEventManagerFactory.getConnectionManager());
	}
//	@Override
//	public void setStateBeforePortCreation() {
////		GlobalDelayState.setDelayServerBufferSends(true);
////		GlobalDelayState.getDelayManager().setMinimumDelay("Alice", 1000);
////		GlobalDelayState.getDelayManager().setMinimumDelay("Bob", 100);
////		GlobalDelayState.getDelayManager().setMinimumDelay("Cathy", 500);
//	}
	
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ALatecomerRelayerAndSessionServerLauncherSupport();
//		return new AReplicatedServerSingleResponsePortLauncherSupport();

	}
	
	
	@Override
	protected ConnectionListener getConnectionListener (InputPort aServerInputPort) {
		return new ATracingConnectionListener((SimplexServerInputPort<ByteBuffer>)aServerInputPort);
	}
	@Override
	protected ReceiveListener getReceiveListener (InputPort aServerInputPort) {
		return new AnEchoingBufferReceiveListener(aServerInputPort);
	}	
	@Override
	protected InputPort getPort() {
		return LatecomerRelayerAndSessionServerSelector.
		createLatecomerSessionsServerAndRelayer(serverPortDescription.getID(), serverPortDescription.getName(), 
				SESSION_SERVER_NAME);
	}

}
