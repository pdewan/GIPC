package extraip;

import port.delay.DelayableMessage;


public interface CopyOfDelayQueue  {	
	void add (DelayableMessage aDelayableMessage);
	DelayableMessage take() throws InterruptedException;
}