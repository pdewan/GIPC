package port.sessionserver;

import java.io.Serializable;
import java.util.List;


public interface JoinInfo extends Serializable{
	public List<SessionParticipantDescription> getMembers();
	public void setMembers(List<SessionParticipantDescription> users);
	public List<SessionParticipantDescription> getClients();
	public void setClients(List<SessionParticipantDescription> users) ;
	
	public List<SessionParticipantDescription> getServers() ;
	public void setServers(List<SessionParticipantDescription> users) ;
	 void setApplicationDefinedState (Serializable aState);
	 Serializable getApplicationDefinedState();


}
