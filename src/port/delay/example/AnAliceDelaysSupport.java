package port.delay.example;

import port.PortLauncherSupport;
import port.delay.DelayUtlity;

public class AnAliceDelaysSupport implements PortLauncherSupport{

	@Override
	public void init() {
		DelayUtlity.getDelayManager().setMinimumDelay("Cathy", 10000);
//		DelayUtlity.getDelayManager().setMinimumDelay("Bob", 1000);
		DelayUtlity.getDelayManager().setDelayVariation(1000); // same for all users so setting it only for Cathy
	}

	@Override
	public void tracePrints() {
		// TODO Auto-generated method stub
		
	}

}
