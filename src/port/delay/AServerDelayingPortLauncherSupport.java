package port.delay;

import port.PortLauncherSupport;

public class AServerDelayingPortLauncherSupport implements PortLauncherSupport{

	@Override
	public void init() {
		DelayUtlity.setDelayServerBufferSends();
	}

	@Override
	public void tracePrints() {
		// TODO Auto-generated method stub
		
	}

}
