package port.sessionserver.relay.asymmetricexample;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import port.relay.Relayer;
import port.sessionserver.AJoinInfo;
import port.sessionserver.JoinInfo;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.asymmetricexample.APrintingSessionObserver;
import sessionport.datacomm.duplex.object.relayed.AMessageWithSource;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public class AJoinerTrackingSessionObserver extends APrintingSessionObserver implements JoinerTrackingSessionObserver {
	String myName;
	Set<String> clients = new HashSet();
	MessageWithSource messageWithSource = null;
	String sessionName;
	Relayer relayerProxy;
//	Map<String, DuplexClientInputPort<Object>> nameToPort = new HashMap();
	public AJoinerTrackingSessionObserver(String aName, String aSessionName) {
		myName = aName;
		messageWithSource = new AMessageWithSource(myName, "Words of Robert Frost Please!" );
		sessionName = aSessionName;

	}
	@Override
	public void joined(SessionParticipantDescription aServerPortDescription) {
		super.joined(aServerPortDescription);
		processSessionMember(aServerPortDescription);
	}
	
	public void processInitialSessionMembers(JoinInfo aJoinInfo) {
		List<SessionParticipantDescription> aServerPortDescriptionList = AJoinInfo.getMembersAndServers(aJoinInfo);
		for (ServerPortDescription aServerPortDescription:aServerPortDescriptionList) {
			processSessionMember(aServerPortDescription);
		}		
	}
	void processSessionMember(ServerPortDescription aServerPortDescription) {		
		if (!myName.equals( aServerPortDescription.getName())) {
			clients.add(aServerPortDescription.getName());
//			relayerProxy.relay(sessionName, aServerPortDescription.getName(), messageWithSource);
			relayerProxy.relay(aServerPortDescription.getName(), messageWithSource);

		
		}
	}
	@Override
	public Set<String> getJoiners() {
		return clients;
	}
	@Override
	public void setRelayerProxy(Relayer aRelayer) {
		relayerProxy = aRelayer;
		
	}
	
	
}
