package port.delay;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.InputPort;
import inputport.datacomm.AnAbstractSendTrapper;
import inputport.datacomm.NamingSender;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import util.misc.HashIdentityMap;
import util.misc.IdentityMap;
import util.trace.Tracer;

// unused class, do not know why it was created when it seems to do the same job as the client trapper
// keeping it around just in case
public class AServerSendMessageDelayingTrapper extends AnAbstractSendTrapper<ByteBuffer, ByteBuffer> implements NamingSender<ByteBuffer>, ConnectionListener {
	Map<String, DelayQueue> nameToDelayQueue = new HashMap();
	IdentityMap<String, Thread> nameToDelayThread = new HashIdentityMap();

	public AServerSendMessageDelayingTrapper(InputPort anInputPort, NamingSender<ByteBuffer> aForwarder) {
		super(aForwarder);
		anInputPort.addConnectionListener(this);

	}
	DelayQueue createDelayQueueAndConsumingThread(String aDestination) {
		DelayQueue delayQueue = new ADelayQueueWithReleaseTimes(aDestination);
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
	public void send(String remoteName, ByteBuffer message) {
		Tracer.info(this + " possibly delaying message " + message + " to " + remoteName);
		int delay = DelayUtlity.getDelayManager().computeDelay(remoteName);
		if (delay < DelayManager.DELAY_THRESHOLD) {
			destination.send(remoteName, message);
			return;
		}
		MessageWithDestination aSendDescription = new AMessageWithDestination(message, remoteName);
		MessageDeliverer aMessageDeliverer = new ASendMessageDeliverer(destination);
		DelayableMessage aDelayableMessage = new ADelayableMessage(aSendDescription, aMessageDeliverer, DelayUtlity.getDelayManager().computeDelay(remoteName));
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
