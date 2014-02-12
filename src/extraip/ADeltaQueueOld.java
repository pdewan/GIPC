package extraip;


import java.util.ArrayList;
import java.util.List;

import port.delay.Delayable;



public class ADeltaQueueOld<ElementType extends Delayable>  {
	List<ElementType> deltaQueue = new ArrayList();
	int prevDelay = 0;	
//	@Override
	public synchronized void insert (ElementType theElement) {
		int index = 0;
		long delay = theElement.getTime();
		for (; index < deltaQueue.size(); index ++) {
			Delayable element = deltaQueue.get(index);
			long nextDelay = element.getTime();
			if (nextDelay > delay) {
				element.setTime(nextDelay - delay);
				break;
			}
			prevDelay += nextDelay;			
		}
		theElement.setTime(delay - prevDelay);
		deltaQueue.add(index, theElement);
		notify();
	}	
//	@Override
	public synchronized ElementType take() {
		try {
		if (deltaQueue.size() == 0)
			wait();
		long headDelay = deltaQueue.get(0).getTime();
		if (headDelay > 0)
			Thread.sleep(headDelay);
		return deltaQueue.remove(0);	
		
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
