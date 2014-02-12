package port.delay;

import java.util.LinkedList;
import java.util.List;

import util.annotations.Visible;
import util.trace.Tracer;



public class ADelayQueueWithReleaseTimes<MessageType> implements DelayQueue<MessageType>{
	List<DelayableMessage> delayQueue = new LinkedList();
	String destination;
	boolean coupled = true;
	boolean delayed = true;
	boolean delayedNotify = false;
	public  ADelayQueueWithReleaseTimes(String aName) {
		destination = aName;
	}
	public boolean getCoupled() {
		return coupled;
	}
	public synchronized void setCoupled(boolean newVal) {
		coupled = newVal;
		if (delayedNotify) {
			notify();
		}
	}
	public boolean getDelayed() {
		return delayed;
	}
	public  void setDelayed(boolean newVal) {
		delayed = newVal;		
	}
	@Visible(false)
	@Override
	public synchronized void add (DelayableMessage<MessageType> anElement) {
//		int delay = (int) anElement.getTime();
//		long currentTime = System.currentTimeMillis();
//		long releaseTime = delay + currentTime;
//		anElement.setTime(releaseTime); // changing from relative to absolute time
//		Tracer.info(this, " At time " + currentTime + " added to " + this + " element " +  anElement + " with delay:" + delay + " and release time:"  + releaseTime);
		if (delayed) {
		delayQueue.add(anElement);
		if (coupled) {
			notify();
		} else {
			delayedNotify = true;
		}
		} else {
			ADelayQueueWithReleaseTimesConsumingRunnable.deliverMessage(anElement);
//			MessageWithDestination sendDescription = anElement.getMessageDescription();
//			anElement.getMessageDeliverer().deliver(sendDescription.getDestination(), sendDescription.getData());
		}
	}	
//	@Override
//	public synchronized DelayableMessage take() {
//		try {
//		Thread currentThread = Thread.currentThread();
//		if (delayQueue.size() == 0) {
//			Message.info(this, currentThread + " waiting for non empty queue");
//			wait();
//		}
//		DelayableMessage element = delayQueue.remove(0);
//		long releaseTime = element.getTime();
//		long delay = element.getTime() - System.currentTimeMillis();
//		if ((delay) >  DelayManager.DELAY_THRESHOLD) {
//			Message.info(this, "At time  " + System.currentTimeMillis()  +  " " + currentThread + " sleeps for delay " + delay);
//			Thread.sleep (delay);
//		}
////		} else {
////			Message.info(this, "At time: " + System.currentTimeMillis()  +  " " + currentThread + "does takes element without sleeping");
////
////		}
//		Message.info(this, "At time: " + System.currentTimeMillis() + " " + currentThread + " takes element: " + element);
//
//		return element;	
//		
////		} catch (InterruptedException ie) {
////			Message.info(this, "Delta queue thread interrupted, taking new head");
////			return take(); // danger with cascaded interruptions?
//		} catch (Exception e) {		
//			e.printStackTrace();
//			return null;
//		}		
//	}
	@Override
	public synchronized DelayableMessage<MessageType> take() throws InterruptedException {
//		try {
		Thread currentThread = Thread.currentThread();
		if (delayQueue.size() == 0) {
			Tracer.info(this, currentThread + " waiting for non empty queue");
			wait();
		}
		return delayQueue.remove(0);
		
		
//		} catch (InterruptedException ie) {
//			Message.info(this, "Delta queue thread interrupted, taking new head");
//			return take(); // danger with cascaded interruptions?
//		} catch (Exception e) {		
//			e.printStackTrace();
//			return null;
//		}		
	}
	
	@Override
	public String toString() {
		return destination + "delay queue";
	}
	
}
