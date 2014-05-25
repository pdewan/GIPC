package port.sessionserver.relay.asymmetricexample;

import inputport.datacomm.ReceiveListener;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public class AnEchoingMessageWithSourceReceiveListener implements ReceiveListener<Object>{

	@Override
	public void messageReceived(String aSourceName, Object aMessage) {
		MessageWithSource aMessageWithSource = (MessageWithSource) aMessage;
		System.out.println(aMessageWithSource.getSource() + "-->" + aSourceName + "-->" + aMessageWithSource.getMessage());
	}

}
