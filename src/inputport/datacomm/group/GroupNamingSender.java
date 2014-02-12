package inputport.datacomm.group;


import inputport.datacomm.NamingSender;

import java.util.Set;


public interface GroupNamingSender<MessageType> 
			extends NamingSender<MessageType> { // naming sender allows relay port to be sent different messages, may also need sendOthers at some point
	void send(Set<String> clientNames, MessageType message);	

}
