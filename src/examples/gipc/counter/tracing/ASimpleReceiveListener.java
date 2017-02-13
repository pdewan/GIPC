package examples.gipc.counter.tracing;

import inputport.datacomm.ReceiveListener;

import java.util.Collection;

import util.session.ReceivedMessageListener;

public class ASimpleReceiveListener implements ReceiveListener {

	@Override
	public void messageReceived(String aSourceName, Object aMessage) {
		System.out.println (aSourceName + "Sent:" + aMessage);
	}

}
