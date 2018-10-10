package inputport.datacomm.group;


import java.util.Collection;

import inputport.datacomm.NamingSender;


public interface GroupNamingSender<MessageType> 
			extends NamingSender<MessageType> { // naming sender allows relay port to be sent different messages, may also need sendOthers at some point
	void send(Collection<String> clientNames, MessageType message);	

}
