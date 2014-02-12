package replicatedserverport.rpc.duplex.singleresponse;

import port.PortLauncherSupport;

public class ASingleResponseReplicatedRelayedDuplexServerLauncherSupport implements PortLauncherSupport{

	@Override
	public void init() {
		SingleResponseReplicatedClientServerUtlity.setSingleResponseRelayedDuplexServerFactories();
		

	}

	@Override
	public void tracePrints() {
		// TODO Auto-generated method stub
		
	}

}
