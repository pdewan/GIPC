package extraip;

import port.sessionserver.AServerPortDescription;
import port.sessionserver.ServerPortDescription;
import inputport.datacomm.simplex.object.example.CathyClientLauncher;

public interface CathyServerLauncher {
	public static final ServerPortDescription SERVER_3_DESCRIPTION = new AServerPortDescription("localhost", "9092", CathyClientLauncher.CATHY);

}
