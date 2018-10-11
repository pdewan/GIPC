package extraip;

import java.nio.ByteBuffer;

import inputport.datacomm.NamingSender;
import port.delay.Delayable;
import port.delay.MessageWithDestination;


public interface CopyOfDelayableMessage  extends Delayable{
	MessageWithDestination getSendDescription();
	void setSendDescription(MessageWithDestination newVal);
	NamingSender<ByteBuffer> getForwarder();
	void setForwarder(NamingSender<ByteBuffer> newVal);	
	
}
