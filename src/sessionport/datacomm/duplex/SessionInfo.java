package sessionport.datacomm.duplex;

import java.util.List;

import port.sessionserver.SessionParticipantDescription;

public interface SessionInfo {
	public List<SessionParticipantDescription> getServers() ;
	public List<SessionParticipantDescription> getMembers() ;
	public List<SessionParticipantDescription> getClients() ;
	public SessionParticipantDescription getServer(String aName);
	public SessionParticipantDescription getClient(String aName) ;
	public SessionParticipantDescription getMember(String aName) ;

}
