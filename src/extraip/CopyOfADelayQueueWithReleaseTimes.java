package extraip;

import java.util.LinkedList;
import java.util.List;

import port.delay.DelayQueue;
import port.delay.DelayableMessage;
import util.trace.Tracer;



public class CopyOfADelayQueueWithReleaseTimes implements DelayQueue{
	List<DelayableMessage> delayQueue = new LinkedList();
	String destination;
	public  CopyOfADelayQueueWithReleaseTimes(String aName) {
		destination = aName;
	}
	
	@Override
	public synchronized void add (DelayableMessage anElement) {
		int delay = (int) anElement.getTime();
		long currentTime = System.currentTimeMillis();
		long releaseTime = delay + currentTime;
		anElement.setTime(releaseTime); // changing from relative to absolute time
		Tracer.info(this, " At time " + currentTime + " added to " + this + " element " +  anElement + " with delay:" + delay + " and release time:"  + releaseTime);
		delayQueue.add(anElement);
		notify();
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
	public synchronized DelayableMessage take() throws InterruptedException {
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

	@Override
	public boolean getCoupled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCoupled(boolean newVal) {
		// TODO Auto-generated method stub
		
	}
	
}
