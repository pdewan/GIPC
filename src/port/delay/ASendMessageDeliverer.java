package port.delay;

import inputport.datacomm.NamingSender;
public class ASendMessageDeliverer implements MessageDeliverer{
//public class ASendMessageDeliverer<MessageType> implements MessageDeliverer<MessageType>{
	NamingSender sender;
	public ASendMessageDeliverer(NamingSender aSender) {
		sender = aSender;
	}
	@Override
	public void deliver(String aDestination, Object aMessage) {
		sender.send(aDestination, aMessage);
	}
	

}
