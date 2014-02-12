package extraip;



import java.util.ArrayList;
import java.util.List;

import port.delay.DelayQueue;
import port.delay.DelayUtlity;
import port.delay.DelayableMessage;
import util.trace.Tracer;


public class ADeltaQueue implements DelayQueue {
	List<DelayableMessage> deltaQueue = new ArrayList();
	int prevSleepTime = 0;
	long sleeperSleepTime;
	int toInt(long num) {
		return (int) num;
	}
	@Override
	public synchronized void add (DelayableMessage theElement) {
		int index = 0;
		long delay = theElement.getTime();
		long currentTime = System.currentTimeMillis();
		long prevWakeupTime = currentTime;
		long currentWakeupTime = currentTime + delay;
		long nextWakeupTime = sleeperSleepTime; // value not used if there is no sleeper
		for (; index < deltaQueue.size(); index ++) {
			// guaranteed that consumer thread is sleeping
			DelayableMessage element = deltaQueue.get(index);
			nextWakeupTime +=  element.getTime();
			if (nextWakeupTime > currentWakeupTime) {
				element.setTime ((int) (nextWakeupTime - currentWakeupTime));
				break;
			}
			prevWakeupTime = nextWakeupTime;			
		}
		int delta = (int) (currentWakeupTime -  prevWakeupTime);
		theElement.setTime(delta);
		deltaQueue.add(index, theElement);
		Tracer.info(this, " At time:" + currentWakeupTime + "inserted: " + theElement + " with delay:" + delay + " and delta:"  + delta + " at index: " + index);
		if (index == 0 && deltaQueue.size() > 1) {
			Thread deltaQueueConsumer = DelayUtlity.getDelayQueueConsumerThread();
			Tracer.info(this, "Interrupting thread waiting for a later message");
			deltaQueueConsumer.interrupt();
//			GlobalState.getDelayQueueConsumerThread().interrupt();
		} else
			notify();
	}	
	@Override
	public synchronized DelayableMessage take() {
		try {
		if (deltaQueue.size() == 0) {
			Tracer.info(this, "Delta queue thread waiting for non empty queue");
			wait();
		}
		long headDelay = deltaQueue.get(0).getTime();
		if (headDelay > 0) {
			sleeperSleepTime = System.currentTimeMillis();
			Tracer.info(this, "At time: " + sleeperSleepTime + " delta queue thread sleeps for delay:" + headDelay);
			Thread.sleep(headDelay);			
			Tracer.info(this, "At time: " + System.currentTimeMillis() + " delta queue thread wakes up");
		}
		return deltaQueue.remove(0);	
		
		} catch (InterruptedException ie) {
			Tracer.info(this, "Delta queue thread interrupted, taking new head");
			return take(); // danger with cascaded interruptions?
		} catch (Exception e) {		
			e.printStackTrace();
			return null;
		}		
	}
//	public synchronized List<Delayable> takList() {
//		try {			
//			if (deltaQueue.size() == 0)
//				wait();
//			int headDelay = deltaQueue.get(0).getDelay();
//			if (headDelay > 0) {
//				Thread.sleep(headDelay);
//			}
//			int lastIndex = 1;
//			for (; lastIndex < deltaQueue.size(); lastIndex++)
//				if (deltaQueue.get(lastIndex).getDelay() > 0)
//					break;
//			List<Delayable> retVal = deltaQueue.subList(0, lastIndex);
//			deltaQueue.remove(retVal);
//			return retVal;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	@Override
	public boolean getCoupled() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setCoupled(boolean newVal) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void insert(ElementType theElement) {
//		// TODO Auto-generated method stub
//		
//	}
//
//@Override
//public ElementType take() {
//	// TODO Auto-generated method stub
//	return null;
//}

}
