package sessionport.datacomm.duplex.object.relayed;

import java.util.List;

import port.sessionserver.AJoinInfo;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionObserver;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.relay.RelayerSupportingSessionServer;


public class ASessionServerJoiningRunnable implements Runnable {
	RelayerSupportingSessionServer sessionServerProxy;
	SessionObserver sessionObserver;
	ServerPortDescription sessionClientDescription;
	String sessionName;
	public  ASessionServerJoiningRunnable( 
			ServerPortDescription aSessionClientDescription,
			String aSessionName,
			SessionObserver aRelayingDuplexConnectionsManager,
			RelayerSupportingSessionServer aSessionServerProxy) {
		sessionClientDescription = aSessionClientDescription;
		sessionName = aSessionName;
		sessionServerProxy = aSessionServerProxy;
		sessionObserver = aRelayingDuplexConnectionsManager;
	}
	public void run() {
		joinSessionServer();
	}
	protected void joinSessionServer() {

		List<SessionParticipantDescription> currentMembers = 
				AJoinInfo.getMembersAndServers(sessionServerProxy.join(sessionName, sessionClientDescription, sessionObserver));

		processCurrentMembers(currentMembers);
	}
	
	protected void processCurrentMembers(List<SessionParticipantDescription> aCurrentMembersList) {
		for (int i=0; i<aCurrentMembersList.size(); i++)
			sessionObserver.joined(aCurrentMembersList.get(i));
	}


}
