package replicatedserverport.rpc.groupserver.singleresponse;

import port.PortLauncherSupport;
import replicatedserverport.rpc.duplex.singleresponse.SingleResponseReplicatedClientServerUtlity;
// should this really be in rpc?
public class ASingleResponseReplicatedGroupServerLauncherSupport implements PortLauncherSupport{

	@Override
	public void init() {
		SingleResponseReplicatedClientServerUtlity.setSingleResponseGroupServerFactories();
		

	}

	@Override
	public void tracePrints() {
		// TODO Auto-generated method stub
		
	}

}
