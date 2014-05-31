package port.sessionserver.example;

import port.AnAbstractPortLauncher;
import port.ParticipantChoice;

public abstract class AnAbstractSessionServerClientLauncher extends AnAbstractPortLauncher{

	public final static String SESSION_NAME = "Test Session";
	public final static String SESSION_SERVER_HOST = "localhost";


//	protected String serverHost, serverId, serverName;
	protected String myId, myName;

//	protected SessionObserver observer;
	protected String myHost;
	protected ParticipantChoice participantChoice;
	public AnAbstractSessionServerClientLauncher(String aSessionServerHost, String aServerId, String aServerName, String aMyId, String aMyName, ParticipantChoice aChoice) {
//		super(aServerName);
		serverHost = aSessionServerHost;
		serverId = aServerId;
		serverName = aServerName;
		myId = aMyId;
		myName = aMyName;
		participantChoice = aChoice;
//		observer = new APeerConnectingSessionObserver(myName);
//		observer = createSessionObserver();
			
	}
	
	
	

}
