package port.delay;

import util.trace.Tracer;


public class ADelayQueueWithReleaseTimesConsumingRunnable implements Runnable {
	DelayQueue delayQueue;
//	boolean execute = true;
//	public void stop() {
//		execute = false;
//	}
	public  ADelayQueueWithReleaseTimesConsumingRunnable(DelayQueue aDelayQueue) {
		delayQueue = aDelayQueue;
	}
	@Override
	public void run() {
//		for (;;) {
////			Message.info(this, "Removed  from delay queue  message " + delayableMessage);
//			DelayableMessage delayableMessage = delayQueue.take();
////			Message.info(this, "Removed  from delay queue  message " + delayableMessage);
//			SendDescription sendDescription = delayableMessage.getSendDescription();
//			delayableMessage.getForwarder().send(sendDescription.getDestination(), sendDescription.getData());
//		}
//	}	
		while (true) {
			try {
			DelayableMessage delayableMessage = delayQueue.take();	
			Thread currentThread = Thread.currentThread();
//			Tracer.info(this, "Removed  from delay queue delayed message " + delayableMessage);			
			long releaseTime = delayableMessage.getTime();
			long currentTime = System.currentTimeMillis();
			long delay = delayableMessage.getTime() - currentTime;
			if ((delay) >  DelayManager.DELAY_THRESHOLD) {
				Tracer.info(this, "At time  " + currentTime +  " " + currentThread + " sleeps for delay " + delay);
				try {
					Thread.sleep (delay);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				Tracer.info(this, "At time  " + currentTime +  " " + currentThread + " does not sleep for delay " + delay);

			}
//			} else {
//				Message.info(this, "At time: " + System.currentTimeMillis()  +  " " + currentThread + "does takes element without sleeping");
	//
//			}
			deliverMessage(delayableMessage);
//			MessageWithDestination sendDescription = delayableMessage.getMessageDescription();
//			delayableMessage.getMessageDeliverer().deliver(sendDescription.getDestination(), sendDescription.getData());
			} catch (InterruptedException e) {
				Tracer.info("Interrupted Delay Thread Terminated");
				break;
			}
			catch (Exception e) {
				e.printStackTrace();
				break;				
			}
		}	
	}
	
	public static void deliverMessage(DelayableMessage delayableMessage) {
		MessageWithDestination sendDescription = delayableMessage.getMessageDescription();
		delayableMessage.getMessageDeliverer().deliver(sendDescription.getDestination(), sendDescription.getData());
	}

}
