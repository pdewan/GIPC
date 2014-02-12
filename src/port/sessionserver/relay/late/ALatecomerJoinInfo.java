package port.sessionserver.relay.late;

import java.io.Serializable;
import java.util.List;

import port.sessionserver.JoinInfo;
import port.sessionserver.SessionParticipantDescription;

import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public class ALatecomerJoinInfo extends ALatecomerMessages implements LatecomerJoinInfo {
//	List<MessageWithSource> messages;
	JoinInfo joinInfo;
	
	public ALatecomerJoinInfo() {
		
	}
	public ALatecomerJoinInfo(JoinInfo aJoinInfo, List<MessageWithSource> aMessageList,
			List <MessageWithSource> aSeverMessageList, 
			List <MessageWithSource> aClientMessageList) {
		super(aMessageList, aSeverMessageList, aClientMessageList );
		joinInfo = aJoinInfo;
	}
	
	public List<SessionParticipantDescription> getMembers() {
		return joinInfo.getMembers();
	}
	public void setMembers(List<SessionParticipantDescription> users) {
		joinInfo.setMembers(users);
	}
	@Override
	public List<SessionParticipantDescription> getClients() {
		return joinInfo.getClients();
	}
	@Override
	public void setClients(List<SessionParticipantDescription> users) {
		joinInfo.setClients(users);
	}
	@Override
	public List<SessionParticipantDescription> getServers() {
		return joinInfo.getServers();
	}
	@Override
	public void setServers(List<SessionParticipantDescription> users) {
		joinInfo.setServers(users);
	}
	@Override
	public void setApplicationDefinedState(Serializable aState) {
		joinInfo.setApplicationDefinedState(aState);
		
	}
	@Override
	public Serializable getApplicationDefinedState() {
		return joinInfo.getApplicationDefinedState();
	}
	

}
