package port.delay.example;

import port.PortLauncherSupport;
import port.delay.DelayUtlity;

public class ABobDelaysSupport implements PortLauncherSupport{

	@Override
	public void init() {
		DelayUtlity.getDelayManager().setMinimumDelay("Alice", 1000);
//		DelayUtlity.getDelayManager().setMinimumDelay("Cathy", 1000);

	}

	@Override
	public void tracePrints() {
		// TODO Auto-generated method stub
		
	}

}
