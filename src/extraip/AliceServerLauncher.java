package extraip;

import port.sessionserver.AServerPortDescription;
import port.sessionserver.ServerPortDescription;
import inputport.datacomm.simplex.object.example.AliceClientLauncher;

public interface AliceServerLauncher {
	public static final ServerPortDescription SERVER_1_DESCRIPTION = new AServerPortDescription("localhost", "9090", AliceClientLauncher.ALICE);

}
