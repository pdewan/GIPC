package port.old;


import inputport.datacomm.duplex.QueryableDuplexServerSender;
import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.group.GroupSender;

import java.util.Set;


public class AGroupSender<MessageType> implements GroupSender<MessageType>{
	QueryableDuplexServerSender<MessageType> duplexSender;
	GroupNamingSender<MessageType> groupNamingSender;	
	public AGroupSender(QueryableDuplexServerSender<MessageType> aDuplexSender, 
			GroupNamingSender<MessageType> aGroupNamingSender) {
		duplexSender = aDuplexSender;
		groupNamingSender = aGroupNamingSender;
	}	
	public void send(MessageType message) {
		duplexSender.send(message);
	}
	public void send(String remoteName, MessageType message) {
		duplexSender.send(remoteName, message);
	}
	public void reply(MessageType message) {
		duplexSender.reply(message);
	}
	@Override
	public void send(Set<String> clientNames, MessageType message) {
		groupNamingSender.send(clientNames, message);
	}
	@Override
	public void sendOthers(MessageType message) {
//		if (duplexSender.getLastSender() == null) throw new NoMessageReceivedByResponderException();
		Set<String> peerNames = duplexSender.getConnections();
//		peerNames.remove(duplexSender.getLastSender());
		send(peerNames, message);
	}
	@Override
	public void sendAll(MessageType message) {
		Set<String> peerNames = duplexSender.getConnections();
		send(peerNames, message);		
	}
	@Override
	public void reply(String aSource, MessageType aMessage) {
		// TODO Auto-generated method stub
		
	}
}
