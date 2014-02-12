package inputport.datacomm.group;

import inputport.datacomm.duplex.DuplexClientInputPortFactory;


public interface GroupInputPortFactory<MessageType> extends  GroupServerInputPortFactory<MessageType>, DuplexClientInputPortFactory {	
}
