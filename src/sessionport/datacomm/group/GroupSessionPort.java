package sessionport.datacomm.group;
import inputport.datacomm.group.GroupServerInputPort;
import multiserverport.datacomm.group.GroupMultiServerClientPort;
import sessionport.datacomm.duplex.DuplexSessionPort;

public interface GroupSessionPort<MessageType> extends 
		GroupServerInputPort<MessageType>, 
		DuplexSessionPort<MessageType>,
		GroupMultiServerClientPort<MessageType>,
		GroupSessionSender<MessageType>{



}
