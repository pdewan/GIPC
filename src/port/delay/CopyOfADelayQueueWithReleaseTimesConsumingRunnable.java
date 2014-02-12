package port.delay;

import util.trace.Tracer;


public class CopyOfADelayQueueWithReleaseTimesConsumingRunnable implements Runnable {
	DelayQueue delayQueue;
//	boolean execute = true;
//	public void stop() {
//		execute = false;
//	}
	public  CopyOfADelayQueueWithReleaseTimesConsumingRunnable(DelayQueue aDelayQueue) {
		delayQueue = aDelayQueue;
	}
	@Override
	public void run() {

		while (true) {
			try {
			DelayableMessage delayableMessage = delayQueue.take();	
//			Thread currentThread = Thread.currentThread();
			Tracer.info(this, "Removed  from delay queue delayed message " + delayableMessage);			
//			long releaseTime = delayableMessage.getTime();
//			long delay = delayableMessage.getTime() - System.currentTimeMillis();
			long delay = delayableMessage.getTime();
			Thread currentThread = Thread.currentThread();
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
	
//	@Override
//	public void run() {
//
//		while (true) {
//			try {
//			DelayableMessage delayableMessage = delayQueue.take();	
//			Thread currentThread = Thread.currentThread();
//			Tracer.info(this, "Removed  from delay queue delayed message " + delayableMessage);			
//			long releaseTime = delayableMessage.getTime();
//			long delay = delayableMessage.getTime() - System.currentTimeMillis();
//			if ((delay) >  DelayManager.DELAY_THRESHOLD) {
//				Tracer.info(this, "At time  " + System.currentTimeMillis()  +  " " + currentThread + " sleeps for delay " + delay);
//				try {
//					Thread.sleep (delay);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
////			} else {
////				Message.info(this, "At time: " + System.currentTimeMillis()  +  " " + currentThread + "does takes element without sleeping");
//	//
////			}
//			Tracer.info(this, "At time: " + System.currentTimeMillis() + " " + currentThread + " forwards element: " + delayableMessage);
//			MessageWithDestination sendDescription = delayableMessage.getMessageDescription();
//			delayableMessage.getMessageDeliverer().deliver(sendDescription.getDestination(), sendDescription.getData());
//			} catch (InterruptedException e) {
//				Tracer.info("Interrupted Delay Thread Terminated");
//				break;
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//				break;				
//			}
//		}	
//	}

}
