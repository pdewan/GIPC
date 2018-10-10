package oldgroupip;


import java.util.Set;

import inputport.datacomm.duplex.DuplexSender;


public interface GroupSend<MessageType> extends DuplexSender<MessageType>  {
	void send(Set<String> clientNames, MessageType message);
	public void sendAll(MessageType message);
	public void sendOthers(MessageType message);

}
