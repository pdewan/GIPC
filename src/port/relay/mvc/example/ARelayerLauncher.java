package port.relay.mvc.example;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.simplex.SimplexServerInputPort;
import inputport.datacomm.simplex.buffer.example.AnEchoingBufferReceiveListener;
import java.nio.ByteBuffer;

import port.ATracingConnectionListener;
import port.AnAbstractPortLauncher;
import port.PortLauncherSupport;
import port.relay.RelayerLauncher;
import port.relay.RelayerPortSelector;
import port.sessionserver.relay.ASessionServerRelayerLauncherSupport;




public class ARelayerLauncher extends AnAbstractPortLauncher implements RelayerLauncher{
	public static void main (String args[]) {	
		(new port.relay.ARelayerLauncher(RELAYER_ID, RELAYER_NAME)).launch();
	}	
	
}
