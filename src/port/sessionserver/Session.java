package port.sessionserver;

import inputport.ConnectionListener;

import java.io.Serializable;
import java.util.List;

import port.ParticipantChoice;


public interface Session extends ConnectionListener{
	List<SessionParticipantDescription> getMembers();
	// they have no server port description, which is an issue for disconnecting also
	List<SessionParticipantDescription> getClients();
	List<SessionParticipantDescription> getServers();
	// should have operation for joining
	// since distributed, redundant join operation
	void join (ServerPortDescription aSessionClientDescription, SessionObserver aSessionObserver);
	void leave(ServerPortDescription aSessionClientDescription);
//	void addObserver(SessionObserver sessionObserver);
	void joinAsServer(ServerPortDescription sessionClientDescription, SessionObserver aSessionObserver);
	void joinAsClient(ServerPortDescription sessionClientDescription, SessionObserver aSessionObserver);
	 ParticipantChoice getParticipantChoice(String aName);
	 void setApplicationDefinedState (Serializable aState);
	 Serializable getApplicationDefinedState();
	
}
