package extraip;

import port.delay.DelayManager;
import port.delay.DelayQueue;
import port.delay.DelayableMessage;
import port.delay.MessageWithDestination;
import util.trace.Tracer;


public class ADelayQueueConsumer implements Runnable{
	DelayQueue delayQueue;
	public  ADelayQueueConsumer(DelayQueue aDelayQueue) {
		delayQueue = aDelayQueue;
	}

	@Override
	public void run() {
		for (;;) {
			try {
			DelayableMessage delayableMessage = delayQueue.take();	
			Thread currentThread = Thread.currentThread();
			Tracer.info(this, "Removed  from delay queue delayed message " + delayableMessage);
			long delay = delayableMessage.getTime();
//			long delay = delayableMessage.getTime() - System.currentTimeMillis();
			if ((delay) >  DelayManager.DELAY_THRESHOLD) {
				Tracer.info(this, "At time  " + System.currentTimeMillis()  +  " " + currentThread + " sleeps for delay " + delay);
				try {
					Thread.sleep (delay);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
//			} else {
//				Message.info(this, "At time: " + System.currentTimeMillis()  +  " " + currentThread + "does takes element without sleeping");
	//
//			}
			Tracer.info(this, "At time: " + System.currentTimeMillis() + " " + currentThread + " forwards element: " + delayableMessage);
			MessageWithDestination sendDescription = delayableMessage.getMessageDescription();
			delayableMessage.getMessageDeliverer().deliver(sendDescription.getDestination(), sendDescription.getData());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}
	

}
