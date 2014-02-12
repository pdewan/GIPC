package replicatedserverport.rpc.duplex.fixedresponse;

import port.PortLauncherSupport;

public class AFixedResponseReplicatedDuplexServerLauncherSupport implements PortLauncherSupport{

	@Override
	public void init() {
		FixedlResponseReplicatedClientServerSupport.setLocalResponseDuplexServerFactories();
		

	}

	@Override
	public void tracePrints() {
		// TODO Auto-generated method stub
		
	}

}
