package port.sessionserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
// unused for now
public class AJoinInfo  implements JoinInfo {
	List<SessionParticipantDescription> members;
	List<SessionParticipantDescription> servers;
	List<SessionParticipantDescription> clients;
	Serializable applicationDefinedState;

	
	public AJoinInfo(List<SessionParticipantDescription> theMembers, 
			List<SessionParticipantDescription> theServers, 
			List<SessionParticipantDescription> theClients, Serializable theState) {
		members = theMembers;
		servers = theServers;
		clients = theClients;
		applicationDefinedState = theState;
		
	}
	public AJoinInfo() {
		
	}
	
	public List<SessionParticipantDescription> getMembers() {
		return members;
	}
	public void setMembers(List<SessionParticipantDescription> users) {
		this.members = users;
	}
	public List<SessionParticipantDescription> getClients() {
		return clients;
	}
	public void setClients(List<SessionParticipantDescription> users) {
		this.clients = users;
	}
	
	public List<SessionParticipantDescription> getServers() {
		return servers;
	}
	public void setServers(List<SessionParticipantDescription> users) {
		this.servers = users;
	}
	
	public static List<SessionParticipantDescription> getMembersAndServers(JoinInfo aJoinInfo) {
		List<SessionParticipantDescription> aSessionParticipantDescriptionList = new ArrayList();
		aSessionParticipantDescriptionList.addAll(aJoinInfo.getMembers());
		aSessionParticipantDescriptionList.addAll(aJoinInfo.getServers());
		return aSessionParticipantDescriptionList;
	}

	public static List<SessionParticipantDescription> getMembersAndClients(JoinInfo aJoinInfo) {
		List<SessionParticipantDescription> aSessionParticipantDescriptionList = new ArrayList();
		aSessionParticipantDescriptionList.addAll(aJoinInfo.getMembers());
		aSessionParticipantDescriptionList.addAll(aJoinInfo.getClients());
		return aSessionParticipantDescriptionList;
	}
	public static List<SessionParticipantDescription> getMembersClientsAndServers(JoinInfo aJoinInfo) {
		List<SessionParticipantDescription> aSessionParticipantDescriptionList = new ArrayList();
		aSessionParticipantDescriptionList.addAll(aJoinInfo.getMembers());
		aSessionParticipantDescriptionList.addAll(aJoinInfo.getClients());
		aSessionParticipantDescriptionList.addAll(aJoinInfo.getServers());
		return aSessionParticipantDescriptionList;
	}

	@Override
	public void setApplicationDefinedState(Serializable aState) {
		applicationDefinedState = aState;
	}

	@Override
	public Serializable getApplicationDefinedState() {
		return applicationDefinedState;
	}

}
