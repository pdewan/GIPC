package port.sessionserver.relay.mvc.example;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.simplex.SimplexServerInputPort;
import inputport.datacomm.simplex.buffer.example.AnEchoingBufferReceiveListener;

import java.nio.ByteBuffer;

import port.ATracingConnectionListener;
import port.PortLauncherSupport;
import port.relay.ARelayerLauncher;
import port.relay.RelayerLauncher;
import port.sessionserver.ASessionServerLauncher;
import port.sessionserver.relay.example.ASessionAndRelayServerClientLauncher;




public class ASessionServerRelayerLauncher {

	public static void main (String args[]) {	
		port.sessionserver.relay.ASessionServerRelayerLauncher.main(args);

	}
	
	
	
	
}
