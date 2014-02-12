package port.delay.example;

import port.PortLauncherSupport;
import port.delay.DelayUtlity;

public class ACathyDelaysSupport implements PortLauncherSupport{

	@Override
	public void init() {
		DelayUtlity.getDelayManager().setMinimumDelay("Alice", 5000);
//		DelayUtlity.getDelayManager().setMinimumDelay("Bob", 1000);

	}

	@Override
	public void tracePrints() {
		// TODO Auto-generated method stub
		
	}

}
