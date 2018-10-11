package inputport.datacomm.group.buffer;

import java.nio.ByteBuffer;
import java.util.Collection;

import inputport.datacomm.NamingSender;
import inputport.datacomm.group.AnAbstractGroupToUniSendTrapper;

public class AGroupToUniSendBufferForwarder extends AnAbstractGroupToUniSendTrapper<ByteBuffer, ByteBuffer>{
	public AGroupToUniSendBufferForwarder(NamingSender<ByteBuffer>  aDestination) {
		 super (aDestination);
	}	
	@Override
	public void send(Collection<String> clientNames, ByteBuffer message) {
		ByteBuffer messageCopy = null;
		int position = 0;
		int limit = 0;
		int retVal = -1;
		for (String clientName:clientNames) {
			if (messageCopy == null) {
				messageCopy = message;
				position = message.position();
				limit = message.limit();				
			} else {
				messageCopy = ByteBuffer.wrap(message.array(), position, limit - position);
			}
			destination.send(clientName, messageCopy);
		}
	}
//	@Override
//	public void send(String aRemoteEnd, ByteBuffer aMessage) {
//		destination.send(aRemoteEnd, aMessage);
//	}
}
