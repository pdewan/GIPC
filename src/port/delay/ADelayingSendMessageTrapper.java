package port.delay;

import java.util.HashMap;
import java.util.Map;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.InputPort;
import inputport.datacomm.AnAbstractSendTrapper;
import inputport.datacomm.NamingSender;
import util.misc.HashIdentityMap;
import util.misc.IdentityMap;
import util.trace.Tracer;


public class ADelayingSendMessageTrapper<MessageType>
	extends AnAbstractSendTrapper<MessageType, MessageType>
	implements ConnectionListener {
	Map<String, DelayQueue> nameToDelayQueue = new HashMap();
	IdentityMap<String, Thread> nameToDelayThread = new HashIdentityMap();
	MessageDeliverer messageDeliverer;
	InputPort inputPort;

	public ADelayingSendMessageTrapper(InputPort anInputPort, NamingSender<MessageType> aDestination) {
		super (aDestination);
		messageDeliverer = new ASendMessageDeliverer(destination);
		anInputPort.addConnectionListener(this);

	}
	DelayQueue createDelayQueueAndConsumingThread(String aDestination) {
//		DelayQueue delayQueue = new ADelayQueueWithReleaseTimes(aDestination);
		DelayQueue delayQueue = SendingDelayQueueSelector.createDelayQueue(inputPort, aDestination);
		
		Runnable delayQueueRunnable = new ADelayQueueWithReleaseTimesConsumingRunnable(delayQueue);
		Thread delayQueueThread = new Thread(delayQueueRunnable);
		delayQueueThread.setName(aDestination + " delay thread");
		nameToDelayThread.put(aDestination, delayQueueThread);
		delayQueueThread.start();
		return delayQueue;		
	}
	DelayQueue getAndMaybeCreateQueue(String aName) {
		DelayQueue retVal = nameToDelayQueue.get(aName);
		if (retVal == null) {
			retVal = createDelayQueueAndConsumingThread(aName);
			nameToDelayQueue.put(aName, retVal);
		}
		return retVal;
	}
	@Override
	public void send(String remoteName, MessageType message) {
//		Tracer.info(this, " possibly delaying message " + message + " to " + remoteName);
		int delayFromCurrentTime = DelayUtlity.getDelayManager().computeDelay(remoteName);
//		if (delayFromCurrentTime < DelayManager.DELAY_THRESHOLD) {
//			destination.send(remoteName, message);
//			return;
//		}
		long currentTime = System.currentTimeMillis();
		Tracer.info(this, " at time " + currentTime + " delaying message " + message + " to " + remoteName + " with delay from current time " + delayFromCurrentTime);

		MessageWithDestination aMessageDescription = new AMessageWithDestination(message, remoteName);

		DelayableMessage aDelayableMessage = new ADelayableMessage(aMessageDescription, messageDeliverer, currentTime + delayFromCurrentTime);

		DelayQueue delayQueue = getAndMaybeCreateQueue(remoteName);
		delayQueue.add(aDelayableMessage);
	}
	@Override
	public void disconnected(String remoteEndName,
			boolean explicitDsconnection, String systemMessage, ConnectionType aConnectionType) {
		nameToDelayQueue.remove(remoteEndName);
		Thread aDelayThread = nameToDelayThread.remove(remoteEndName);
		if (aDelayThread != null) {
			Tracer.info("Terminating delay thread for disconnected remote end " + remoteEndName);
			aDelayThread.interrupt();
		}
		
	}
	@Override
	public void connected(String remoteEnd, ConnectionType aConnectionType) {
		
	}
	
	@Override
	public void notConnected(String remoteEnd, String message, ConnectionType aConnectionType) {
		
	}

}
