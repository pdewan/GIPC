package port.sessionserver.relay.late.mvc.dynamic.example;

import java.io.Serializable;
import java.util.List;

import inputport.ConnectionListenerWithPort;
import inputport.ConnectionType;
import inputport.datacomm.ReceiveListener;
import port.ParticipantChoice;
import port.relay.mvc.example.GenericRelayingCollaborativeFrostyModel;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.relay.late.mvc.example.ALatecomerRelayerConnectionListener;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public class ADynamicServerSessionObserver extends ALatecomerRelayerConnectionListener implements DynamicServerSessionObserver  {
	String serverName;
	
	public ADynamicServerSessionObserver(String aServerName, ServerPortDescription aServerPortDescription, String aClientName,
			ConnectionListenerWithPort aConnectionListener, GenericRelayingCollaborativeFrostyModel aModel, 
			List<MessageWithSource> aMessageList, ReceiveListener aSessionJoiner) {
		super(aServerPortDescription, aClientName, aConnectionListener, aModel, aMessageList, aSessionJoiner);
		serverName = aServerName;		
	}
	@Override
	public void joined(SessionParticipantDescription aSessionParticipantDescription) {
		System.out.println("Session member has joined: " + aSessionParticipantDescription);
		if (aSessionParticipantDescription.getParticipantChoice() == ParticipantChoice.SERVER_ONLY ) { // server 
			initNextServerPortDescription(aSessionParticipantDescription);
			// delayed the fake connect until we have the server port description
			connected(serverName, ConnectionType.CLIENT_TO_SESSION); 
		}
	}	
	
	@Override
	public void left(SessionParticipantDescription aSessionParticipantDescription) {
		System.out.println("Session member has left: " + aSessionParticipantDescription);
		
	}
	@Override
	public void newState(Serializable newState) {
		
	}
	
	
}
