package extraip;

import java.nio.ByteBuffer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.simplex.buffer.AClientChannelSendBufferForwarder;
import inputport.datacomm.simplex.buffer.SimplexBufferClientInputPortDriver;

public class AClientChannelBufferDelayer<ChannelType> extends AnAbstractDelayer implements NamingSender<ByteBuffer>{
//	UniNamingSender<Object> forwarder;
//	DelayQueue delayQueue;
//	DelayManager delayManager;
	public AClientChannelBufferDelayer(InputPort anInputPort, SimplexBufferClientInputPortDriver<ChannelType> aClientInputDriver) {
		forwarder = new AClientChannelSendBufferForwarder<ChannelType>(anInputPort,aClientInputDriver);
//		delayManager = GlobalState.getDelayManager();
//		delayQueue = GlobalState.getDelayQueue();
	}
//	@Override
//	public void send(String remoteName, Object message) {
//		Message.info("Delaying client channel message: " + message + " to:" + remoteName );
//		SendDescription aSendDescription = new ASendDescription(message, remoteName);
//		DelayableMessage aDelayableMessage = new ADelayableMessage(aSendDescription, forwarder, GlobalState.getDelayManager().computeDelay(remoteName));
//		GlobalState.getDelayQueue().insert(aDelayableMessage);
//	}

}
