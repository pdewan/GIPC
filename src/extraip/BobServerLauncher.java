package extraip;

import inputport.datacomm.simplex.object.example.BobClientLauncher;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.ServerPortDescription;

public interface BobServerLauncher {
	public static final ServerPortDescription SERVER_2_DESCRIPTION = new AServerPortDescription("localhost", "9091", BobClientLauncher.BOB);

}
