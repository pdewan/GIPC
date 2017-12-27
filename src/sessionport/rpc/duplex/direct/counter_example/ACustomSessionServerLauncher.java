package sessionport.rpc.duplex.direct.counter_example;

import inputport.nio.AScatterGatherSelectionManager;
import port.sessionserver.ASessionServerLauncher;
import serialization.SerializerSelector;
import util.trace.Tracer;

public class ACustomSessionServerLauncher extends ASessionServerLauncher{

	public ACustomSessionServerLauncher(String aSessionServerId,
			String aSessionServerName, String aLogicalServerName) {
		super(aSessionServerId, aSessionServerName, aLogicalServerName);
	}
	public ACustomSessionServerLauncher(String aSessionServerId,
			String aSessionServerName) {
		super(aSessionServerId, aSessionServerName);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void initPortLaucherSupports() {
		super.initPortLaucherSupports();		
	}
	public static void main (String args[]) {	
		AScatterGatherSelectionManager.setMaxOutstandingWrites(1000);

		(new ACustomSessionServerLauncher(SESSION_SERVER_ID, SESSION_SERVER_NAME)).launch();
	}
	

}
