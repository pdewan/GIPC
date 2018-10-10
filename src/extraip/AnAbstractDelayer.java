package extraip;

import java.nio.ByteBuffer;

import inputport.datacomm.NamingSender;
import port.delay.ADelayableMessage;
import port.delay.AMessageWithDestination;
import port.delay.ASendMessageDeliverer;
import port.delay.DelayUtlity;
import port.delay.DelayableMessage;
import port.delay.MessageWithDestination;
import util.trace.Tracer;



public class AnAbstractDelayer implements NamingSender<ByteBuffer> {
	NamingSender<ByteBuffer> forwarder;
	@Override
	public void send(String remoteName, ByteBuffer message) {
		Tracer.info(this, "Delaying  message: " + message + " to:" + remoteName );
		MessageWithDestination aSendDescription = new AMessageWithDestination(message, remoteName);
		DelayableMessage aDelayableMessage = new ADelayableMessage(aSendDescription, new ASendMessageDeliverer/*<ByteBuffer>*/(forwarder), DelayUtlity.getDelayManager().computeDelay(remoteName));
		DelayUtlity.getDelayQueue().add(aDelayableMessage);
	}

}
