package port.sessionserverAndRelay.mvc.example;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.simplex.SimplexServerInputPort;
import inputport.datacomm.simplex.buffer.example.AnEchoingBufferReceiveListener;

import java.nio.ByteBuffer;

import port.ATracingConnectionListener;
import port.PortLauncherSupport;
//import port.sessionserver.ASessionServerLauncher;
import port.sessionserver.ASessionServerLauncherSupport;
import port.sessionserver.SessionServerSelector;




public class ASessionServerLauncher  {

	public static void main (String args[]) {	
		 port.sessionserver.ASessionServerLauncher.main(args);
	}

	
}
