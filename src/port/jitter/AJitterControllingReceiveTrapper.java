package port.jitter;

import inputport.InputPort;
import inputport.datacomm.AnAbstractReceiveTrapper;
import inputport.datacomm.ReceiveNotifier;
import port.delay.ADelayQueueWithReleaseTimesConsumingRunnable;
import port.delay.ADelayableMessage;
import port.delay.AMessageWithDestination;
import port.delay.DelayManager;
import port.delay.DelayQueue;
import port.delay.DelayableMessage;
import port.delay.MessageDeliverer;
import port.delay.MessageWithDestination;
import util.trace.Tracer;

public class AJitterControllingReceiveTrapper extends AnAbstractReceiveTrapper<Object, Object>{
	
	long lastMessageSendTime;
	long lastMessageDeliveryTime;
//	int lastDelay;
	Thread delayQueueThread;
	DelayQueue delayQueue;
	MessageDeliverer messageDeliverer;

	public AJitterControllingReceiveTrapper(InputPort anInputPort,
			ReceiveNotifier<Object> aDestination) {
		super(anInputPort, aDestination);
	    messageDeliverer = new AReceiveMessageDeliverer(aDestination);
		delayQueue = createDelayQueueAndConsumingThread();
		
		
	}
	DelayQueue createDelayQueueAndConsumingThread() {
//		DelayQueue delayQueue = new ADelayQueueWithReleaseTimes(inputPort.getLocalName());
		DelayQueue delayQueue = JitterDelayQueueSelector.createDelayQueue(inputPort, inputPort.getLocalName());
		Runnable delayQueueRunnable = new ADelayQueueWithReleaseTimesConsumingRunnable(delayQueue);
		delayQueueThread = new Thread(delayQueueRunnable);
		delayQueueThread.setName(" Jitter Delay Thread");
		delayQueueThread.start();
		return delayQueue;		
	}

	@Override
	public void notifyPortReceive(String aRemoteEnd, Object aMessage) {
		if (!(aMessage instanceof MessageWithTimeStamp)) {
			destination.notifyPortReceive(aRemoteEnd, aMessage);
			return;
		}
		long currentTime = System.currentTimeMillis();
		MessageWithTimeStamp aMessageWithTimeStamp = (MessageWithTimeStamp) aMessage;
		long timeStamp = aMessageWithTimeStamp.getTimeStamp();
		Object wrappedMessage = aMessageWithTimeStamp.getMessage();
//		System.out.println("Last time stamp:" + lastMessageSendTime);
//		System.out.println ("Current time stamp:" + timeStamp);
		long sendingDelay = (timeStamp - lastMessageSendTime);
		Tracer.info(this, "Sending inter message delay:" + sendingDelay + " for message:" + aMessage);		
//		System.out.println("Last message delivery time:" + lastMessageDeliveryTime);
//		System.out.println("Current time:" + currentTime);
//		System.out.println("Receiving delay:" + (currentTime - lastMessageDeliveryTime) );
		long messageDeliveryTime = lastMessageDeliveryTime + sendingDelay;
		long delay = (messageDeliveryTime - currentTime); // optimization step to avoid context switches
		Tracer.info(this, "Delay with respect to current time:" +  delay);
		// cannot do this optimization because it will cause messages to be out of order
//		if (delay < DelayManager.DELAY_THRESHOLD || lastMessageDeliveryTime == 0) {
//			Tracer.info(this, "Message will not be delayed");
//			destination.notifyPortReceive(aRemoteEnd, wrappedMessage);	
//			messageDeliveryTime = currentTime;
//			delay = 0;
//			
//		} else {
//			MessageWithDestination aMessageDescription = new AMessageWithDestination(wrappedMessage, aRemoteEnd);
//			DelayableMessage aDelayableMessage = new ADelayableMessage(aMessageDescription, messageDeliverer, messageDeliveryTime);
//			delayQueue.add(aDelayableMessage);
//			messageDeliveryTime = currentTime + delay;
//			
//		}
		if (delay < DelayManager.DELAY_THRESHOLD || lastMessageDeliveryTime == 0) {
			Tracer.info(this, "Message will not be delayed");
//			destination.notifyPortReceive(aRemoteEnd, wrappedMessage);	
			messageDeliveryTime = currentTime;
//			delay = 0;
			
		} else {
//			MessageWithDestination aMessageDescription = new AMessageWithDestination(wrappedMessage, aRemoteEnd);
//			DelayableMessage aDelayableMessage = new ADelayableMessage(aMessageDescription, messageDeliverer, messageDeliveryTime);
//			delayQueue.add(aDelayableMessage);
			messageDeliveryTime = currentTime + delay;
			
		}
		MessageWithDestination aMessageDescription = new AMessageWithDestination(wrappedMessage, aRemoteEnd);
		DelayableMessage aDelayableMessage = new ADelayableMessage(aMessageDescription, messageDeliverer, messageDeliveryTime);
		delayQueue.add(aDelayableMessage);
		lastMessageDeliveryTime = messageDeliveryTime;
		lastMessageSendTime = Math.max(lastMessageSendTime, timeStamp); // can have messages from multiple sources
		Tracer.info(this, "last message delivery time:" + lastMessageDeliveryTime);
		Tracer.info(this, "last message send time:" + lastMessageSendTime);
//		System.out.println ("delay - last delay" + (delay - lastDelay));
//		lastDelay = delay;
	}

}
