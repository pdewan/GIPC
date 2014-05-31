package port.sessionserver.relay.example;


import java.util.List;

import port.relay.Relayer;
import port.sessionserver.AJoinInfo;
import port.sessionserver.JoinInfo;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.example.APrintingSessionObserver;
import port.sessionserver.example.JoinerProcessingSessionObserver;
import sessionport.datacomm.duplex.object.relayed.AMessageWithSource;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public class AFrostyPeerSendingSessionObserver extends APrintingSessionObserver implements JoinerProcessingSessionObserver {
	String myName;
	Relayer relayer;
	String sessionName;
//	Map<String, DuplexClientInputPort<Object>> nameToPort = new HashMap();
	public AFrostyPeerSendingSessionObserver(String aName, String aSessionName, Relayer aRelayer) {
		myName = aName;
		relayer = aRelayer;
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
		MessageWithSource messageWithSource = new AMessageWithSource(myName, "Words of Robert Frost Please!" );
		if (!myName.equals( aServerPortDescription.getName())) {
//			relayer.relay(sessionName, aServerPortDescription.getName(),messageWithSource );
			relayer.relay(aServerPortDescription.getName(),messageWithSource );

		}
	}

}
