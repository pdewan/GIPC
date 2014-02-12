package oldgroupip;


import inputport.datacomm.duplex.DuplexSender;

import java.util.Set;


public interface GroupSend<MessageType> extends DuplexSender<MessageType>  {
	void send(Set<String> clientNames, MessageType message);
	public void sendAll(MessageType message);
	public void sendOthers(MessageType message);

}
