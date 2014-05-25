package replicatedserverport.rpc.duplex.singleresponse;

import port.PortLauncherSupport;

public class ASingleResponseReplicatedDuplexServerLauncherSupport implements PortLauncherSupport{

	@Override
	public void init() {
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(this.getClass(), true);
		SingleResponseReplicatedClientServerUtlity.setSingleResponseDuplexServerFactories();
		

	}

	@Override
	public void tracePrints() {
		// TODO Auto-generated method stub
		
	}

}
