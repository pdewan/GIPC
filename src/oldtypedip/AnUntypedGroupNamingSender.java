package oldtypedip;


import inputport.datacomm.duplex.DuplexSender;
import inputport.datacomm.group.GroupNamingSender;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Set;



public class AnUntypedGroupNamingSender implements GroupNamingSender<ByteBuffer>{
	DuplexSender<ByteBuffer> duplexSender;
	public AnUntypedGroupNamingSender(DuplexSender<ByteBuffer> aDuplexSender) {
		duplexSender = aDuplexSender;
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
				messageCopy = ByteBuffer.wrap(message.array(), position, limit);
			}
			duplexSender.send(clientName, messageCopy);			
		}
	}
	@Override
	public void send(String remoteEnd, ByteBuffer message) {
		// TODO Auto-generated method stub
		
	}

}
