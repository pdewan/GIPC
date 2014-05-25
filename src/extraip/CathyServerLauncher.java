package extraip;

import inputport.datacomm.simplex.object.example.CathyClientLauncher;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.ServerPortDescription;

public interface CathyServerLauncher {
	public static final ServerPortDescription SERVER_3_DESCRIPTION = new AServerPortDescription("localhost", "9092", CathyClientLauncher.CATHY);

}
