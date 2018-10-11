package port.delay;

import java.nio.ByteBuffer;

import extraip.ADeltaQueue;
import inputport.datacomm.ASendMessageForwarderFactory;
import inputport.datacomm.duplex.buffer.DuplexBufferServerIPTrapperSelector;
import inputport.datacomm.simplex.buffer.SimplexBufferClientIPTrapperSelector;
import inputport.datacomm.simplex.buffer.SimplexBufferServerIPTrapperSelector;
import util.trace.Tracer;
// does not have instance methods as it cannot be inherited
// with the main launching support
public class DelayUtlity {

	public static void setDelayClientBufferSends(boolean yes) {
			if (yes) {
				Tracer.info("Delaying client buffer sends");
				SimplexBufferServerIPTrapperSelector.getTrapperSelector().
				setSendTrapperFactory(
						new ADelayingSendMessageTrapperFactory<ByteBuffer>());
	
	//			clientBufferIPTrapperSelector.setSendTrapperFactory(new ASendMessageDelayingTrapperFactory<ByteBuffer>());
				SimplexBufferClientIPTrapperSelector.getTrapperSelector().
				setSendTrapperFactory(new ADelayingSendMessageTrapperFactory<ByteBuffer>());
	
				
	//			ClientChannelBufferSendTrapperSelector.setClientChannelBufferSendTrapperFactory(
	//					new ASendMessageDelayingTrapperFactory());
			} else {
				Tracer.info("Not delaying client buffer sends");
				SimplexBufferServerIPTrapperSelector.getTrapperSelector().
				setSendTrapperFactory(new ASendMessageForwarderFactory<ByteBuffer>());
				SimplexBufferClientIPTrapperSelector.getTrapperSelector().
//				setSendTrapperFactory(new ASendMessageDelayingTrapperFactory<ByteBuffer>());
//				GlobalState.clientBufferIPTrapperSelector.
				setSendTrapperFactory(new ASendMessageForwarderFactory<ByteBuffer>());
	//			ClientChannelBufferSendTrapperSelector.setClientChannelBufferSendTrapperFactory(
	//					new AClientChannelSendBufferForwarderFactory());
			}		
		}
	public static void setDelayClientBufferSends() {
			Tracer.info("Delaying client buffer sends");
			SimplexBufferServerIPTrapperSelector.getTrapperSelector().
			setSendTrapperFactory(
					new ADelayingSendMessageTrapperFactory<ByteBuffer>());

			SimplexBufferClientIPTrapperSelector.getTrapperSelector().
			setSendTrapperFactory(new ADelayingSendMessageTrapperFactory<ByteBuffer>());

				
	}

	public static void setDelayServerBufferSends() {
			
		Tracer.info("Delaying server buffer sends");
	
		DuplexBufferServerIPTrapperSelector.getTrapperSelector().setSendTrapperFactory(new ADelayingSendMessageTrapperFactory<ByteBuffer>());
	
			
		}

	public static DelayManager getDelayManager() {
		return DelayUtlity.delayManager;
	}

	public static DelayManager delayManager = new ADelayManager();

	public static void setDelayManager(DelayManager newVal) {
		Tracer.info("Setting up delayer nmanager " + newVal);
		delayManager = newVal;
	}

	public static DelayQueue getDelayQueue() {
		return DelayUtlity.delayQueue;
	}

	public static void setDelayQueue(DelayQueue newVal) {
		DelayUtlity.delayQueue = newVal;
	}

	public static void getDelayQueue(DelayQueue newVal) {
		DelayUtlity.delayQueue = newVal;
	}

	public static Thread getDelayQueueConsumerThread() {
		return DelayUtlity.delayQueueConsumerThread;
	}

	public static DelayQueue delayQueue = new ADeltaQueue();
	static Runnable delayQueueConsumer;
	public static Thread delayQueueConsumerThread;
	
}
