package inputport.datacomm.group;


import inputport.datacomm.duplex.DuplexSender;


public interface GroupSender<MessageType> extends 
	DuplexSender<MessageType>, 
	GroupNamingSender<MessageType>,
	GroupOthersSender<MessageType>,
	GroupAllSender<MessageType> {
	
}
