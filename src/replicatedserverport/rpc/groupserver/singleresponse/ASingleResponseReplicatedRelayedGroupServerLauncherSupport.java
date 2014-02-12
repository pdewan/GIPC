package replicatedserverport.rpc.groupserver.singleresponse;

import port.PortLauncherSupport;
import replicatedserverport.rpc.duplex.singleresponse.ASingleResponseReplicatedDuplexServerLauncherSupport;
import replicatedserverport.rpc.duplex.singleresponse.SingleResponseReplicatedClientServerUtlity;
import util.trace.Tracer;

public class ASingleResponseReplicatedRelayedGroupServerLauncherSupport implements PortLauncherSupport{

	@Override
	public void init() {
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(this, true);
//		Tracer.setKeywordPrintStatus(ASingleResponseReplicatedDuplexServerLauncherSupport.class, true);
		SingleResponseReplicatedClientServerUtlity.setSingleResponseRelayedGroupServerFactories();
		

	}

	@Override
	public void tracePrints() {
		// TODO Auto-generated method stub
		
	}

}
