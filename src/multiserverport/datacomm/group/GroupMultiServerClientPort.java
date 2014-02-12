package multiserverport.datacomm.group;

import inputport.datacomm.group.GroupSender;
import inputport.datacomm.group.GroupTrapperSetter;
import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;

public interface GroupMultiServerClientPort<MessageType> extends 
			DuplexMultiServerClientPort<MessageType>, 
			GroupSender<MessageType>, 
			GroupTrapperSetter<MessageType> {

}
